
using System;
using System.Threading;
using System.Collections.Generic;


using SapoBrokerClient;
using SapoBrokerClient.Utils;
using SapoBrokerClient.Encoding;
using SapoBrokerClient.Networking;
using SapoBrokerClient.Authentication;


namespace SapoBrokerClient.Messaging
{
    public delegate void ExceptionHandler(Exception exception);
    public delegate void FaultHandler(NetFault fault);
    public delegate void PongHandler(NetPong pong);
    public delegate void CommunicationFailed();
    
    public class BrokerProtocolHandler
	{
		private static readonly log4net.ILog log = log4net.LogManager.GetLogger(System.Reflection.MethodBase.GetCurrentMethod().DeclaringType);

        public static NetNotification UnblockNotification = new NetNotification("UnblockNotification", NetAction.DestinationType.QUEUE, null, null);
		
		#region Private data members
		private NetworkHandler networkHandler;
		private IMessageSerializer messageSerializer;
		
		private IDictionary<string, Subscription> subscriptions = new Dictionary<string, Subscription>();
        private IDictionary<string, NetMessage> syncSubscriptions = new Dictionary<string, NetMessage>();

        // Send-related fieds. Ensures that only one message is sent at a time and ensures that no message are sent during reconnect.
        object sendLock = new Object(); // send-related operations should lock/synch in this object.
        bool sendSuspended = false; // informs is send is suspended (reconnecting) or not.
        bool sendOk; // after failure, is it ok to send message?
		
		#endregion
				
		#region Auxiliar methods
		
		private byte[] EncodeMessage(NetMessage message)
		{
			return messageSerializer.Marshall(message);
		}
		
		private NetMessage DecodeMessage(byte[] encodedData)
		{
			return messageSerializer.Unmarshall(encodedData);
		}

        private void DealWithException(Exception exception)
		{
            log.Error("Error", exception);
            ExceptionHandler handler = OnException;
            if (handler != null)
                OnException(exception);
		}

           

		#endregion


        #region Delegates and Events

        public event ExceptionHandler OnException;
        public event FaultHandler OnFault;
        public event PongHandler OnPong;
        public event CommunicationFailed OnCommunicationFailed;

        #endregion

        #region Constructors
        public BrokerProtocolHandler(IMessageSerializer messageSerializer, NetworkHandler networkHandler)
		{
			this.messageSerializer = messageSerializer;
            this.networkHandler = networkHandler;
			
			networkHandler.MessageReceived += delegate(byte[] encodedData) {
				try{
					NetMessage netMessage = DecodeMessage(encodedData);
					
					HandleIncommingMessage(netMessage);					
				}catch(Exception ex){
					DealWithException(ex);
				}
			};

            networkHandler.IoFailed += new NetworkHandler.IoFailureHandler(IoFailHandler);
			
			networkHandler.Start();
		}

        
		
		#endregion

        private void SendSubscriptions()
        {
            lock (this)
            {
                foreach (KeyValuePair<string, Subscription> subscription in subscriptions)
                {
                    this.HandleOutgoingMessage(subscription.Value.ToNetMessage(), null);
                }

                lock (syncSubscriptions)
                {
                    foreach (KeyValuePair<string, NetMessage> syncSubscription in syncSubscriptions)
                    {
                        this.HandleOutgoingMessage(syncSubscription.Value, null);
                    }
                }
            }
        }

        private void IoFailHandler(NetworkHandler.IoSyncStatus syncStatus)
        {
            lock (sendLock)
            {
                sendSuspended = true;
                sendOk = false;
            }
            syncStatus.OnChange.OnEvent += delegate(NetworkHandler.IoStatus status)
            {
                if (status == NetworkHandler.IoStatus.Ok)
                {
                    log.Info("Connection re-established");

                    lock (sendLock)
                    {
                        this.sendSuspended = false;
                        Monitor.PulseAll(sendLock);
                    }

                    if (usingAuth)
                    {
                        lock (this)
                        {
                            Authenticate(this.provider, this.clientAuthInfo);
                        }
                    }
                                        
                    SendSubscriptions();
                }
                else
                {
                    log.Error("Communication Failed");
                    
                    if( OnCommunicationFailed != null)
						OnCommunicationFailed();
                }
            };
        }
		
		#region Incomming messages
		
		public void HandleIncommingMessage(NetMessage message)
		{
            if (log.IsInfoEnabled) log.DebugFormat("Handling incomming message! Message type: {0}", message.Action.Action.ToString());
			try{
				switch( message.Action.Action )
				{
				case NetAction.ActionType.ACCEPTED:
					PendingAcceptRequestsManager.MessageReceived(message.Action.AcceptedMessage.ActionId);
					break;
				case NetAction.ActionType.FAULT:
                    HandleFault(message);
                    break;
				case NetAction.ActionType.NOTIFICATION:
					HandleNotification(message);
					break;
				case NetAction.ActionType.PONG:
                    HandlePongMessage(message);
					break;
				default:
					DealWithException( new Exception(String.Format("Unexpected incoming message type: {0}", message.Action.Action)));
					break;
				}
			} catch(Exception ex) {
				DealWithException( new Exception("Error handling incomming message.", ex));
			}
		}
		
		private void HandleNotification(NetMessage message)
		{
			string subscription = message.Action.NotificationMessage.Subscription;
			
            if (!NotifiableKeyedQueues<NetNotification>.Offer(message.Action.NotificationMessage.Destination, message.Action.NotificationMessage))
            {
                lock (subscriptions)
                {
                    if (subscriptions.ContainsKey(subscription))
                    {
                        subscriptions[subscription].FireOnMessage(message.Action.NotificationMessage);
                    }
                    else
                    {
                       DealWithException( new UnexpectedMessageException( "No registered subscribers for received message.", message));
                    }
                }
            }
		}

        private void HandlePongMessage(NetMessage message)
        {
            PongHandler handler = OnPong;
            if (handler != null)
                OnPong(message.Action.PongMessage);
        }

        private void HandleFault(NetMessage message)
        {
            if( message.Action.FaultMessage.Code.Equals( NetFault.PollTimeoutErrorMessage.Action.FaultMessage.Code ) )
            {
                if (NotifiableKeyedQueues<NetNotification>.Offer(message.Action.FaultMessage.Detail, UnblockNotification))
                    return;
            }
            if (!PendingAcceptRequestsManager.MessageFailed(message.Action.FaultMessage))
            {
                FaultHandler handler = OnFault;
                if (handler != null)
                    OnFault(message.Action.FaultMessage);
            }
        }

        
		
		#endregion
		
		#region Outgoing messages

        public static void SendMessageOverUdp(NetMessage message, HostInfo hostInfo, IMessageSerializer messageSerializer)
        {
            byte[] encodedData = messageSerializer.Marshall(message);
            UdpNetworkHandler.SendMessage(encodedData, hostInfo, messageSerializer);
        }

		public bool HandleOutgoingMessage(NetMessage message, AcceptRequest acceptRequest)
		{
			if( acceptRequest != null)
			{
                PendingAcceptRequestsManager.AddAcceptRequest(acceptRequest);
			}

            // transform and Marshall message
            byte[] encodedData = EncodeMessage(message);

            return SendMessage(encodedData);
        }

        private bool SendMessage(byte[] encoded)
        {
            lock (sendLock)
            {
                if(sendSuspended)
                {
                    Monitor.Wait(sendLock);
                    if (!this.sendOk)
                        return false;
                }
                // send message
                 return networkHandler.SendMessage(encoded, this.messageSerializer);
            }
        }


        #endregion

        private ICredentialsProvider provider;
        private AuthenticationInfo clientAuthInfo;
        private bool usingAuth = false;

        
        public bool Authenticate(ICredentialsProvider provider, AuthenticationInfo authInfo)
        {
            Console.WriteLine("Authenticating");
            if (authInfo == null)
            {
                throw new ArgumentNullException("AuthenticationInfo can not be null in order to authenticate.");
            }

            AuthenticationInfo authInfoToUse = authInfo;
            if (provider != null)
            {
                try
                {
                    authInfoToUse = provider.GetCredentials(authInfo);
                    if (authInfoToUse.UserAuthenticationType == null)
                        authInfoToUse.UserAuthenticationType = provider.AuthenticationType;
                }
                catch (InvalidCredentialsException)
                {
                    return false;
                }
            }
            else
            {
                authInfoToUse = authInfo;
            }

            // save important information
            lock (this)
            {
                this.provider = provider;
                this.clientAuthInfo = authInfo;
                this.usingAuth = true;
            }

            // build NetMessage

            string actionId = System.Guid.NewGuid().ToString();

            NetAuthentication netAuth = new NetAuthentication(authInfoToUse.Token, authInfoToUse.UserAuthenticationType);
            if ((authInfoToUse.Roles != null) && (authInfoToUse.Roles.Count != 0))
                netAuth.Roles = authInfoToUse.Roles;
            if (authInfoToUse.UserId != null)

                netAuth.UserId = authInfoToUse.UserId;

            netAuth.ActionId = actionId;

            NetAction netAction = new NetAction(NetAction.ActionType.AUTH);
            netAction.AuthenticationMessage = netAuth;
            NetMessage msg = new NetMessage(netAction);

            // build waitable object
            WaitMessageAccepted waitMsgAccepted = new WaitMessageAccepted();
            AcceptRequest acceptRequest = new AcceptRequest(actionId, waitMsgAccepted, 7000);
            
            //send message
            HandleOutgoingMessage(msg, acceptRequest);

            // wait for response
            lock (waitMsgAccepted.SyncObject)
            {
                Monitor.Wait(waitMsgAccepted.SyncObject);
            }
            if (waitMsgAccepted.WaitResult != WaitMessageAccepted.Result.Accepted)
            {
                log.Error("Authenticatation failed. Reason: " + waitMsgAccepted.WaitResult);
                return false;
            }

            Console.WriteLine("Authenticated");

            return true;
        }

		
		#region Other
		
		public void AddSubscription(Subscription subscription)
		{
			lock(subscriptions){
				subscriptions.Add(subscription.DestinationPattern , subscription);
			}
		}
		
		public void RemoveSubscription(Subscription subscription)
		{
			lock(subscriptions){
				subscriptions.Remove(subscription.DestinationPattern);
			}
		}

        //syncSubscriptions

        public void AddSyncSubscription(string queueName, NetMessage message)
        {
            lock (syncSubscriptions)
            {
                if (syncSubscriptions.ContainsKey(queueName))
                    throw new ArgumentException("Queue " + queueName + " has already a poll runnig.");
                syncSubscriptions.Add(queueName, message);
            }
        }

        public void RemoveSyncSubscription(string queueName)
        {
            lock (syncSubscriptions)
            {
                syncSubscriptions.Remove(queueName);
            }
        }

        public int ReconnectionRetries
        {
            get { return networkHandler.ReconnectionRetries; }
            set { networkHandler.ReconnectionRetries = value; }
        }

        public void Close()
        {
            networkHandler.Close();
        }

		#endregion
	}
	
}
