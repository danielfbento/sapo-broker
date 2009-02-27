package pt.com.xml.codec;

import java.nio.charset.Charset;
import java.util.Date;

import org.caudexorigo.text.DateUtil;

import pt.com.types.NetAccepted;
import pt.com.types.NetAcknowledgeMessage;
import pt.com.types.NetAction;
import pt.com.types.NetBrokerMessage;
import pt.com.types.NetFault;
import pt.com.types.NetMessage;
import pt.com.types.NetNotification;
import pt.com.types.NetPing;
import pt.com.types.NetPoll;
import pt.com.types.NetPong;
import pt.com.types.NetPublish;
import pt.com.types.NetSubscribe;
import pt.com.types.NetUnsubscribe;
import pt.com.xml.Accepted;
import pt.com.xml.Acknowledge;
import pt.com.xml.BrokerMessage;
import pt.com.xml.CheckStatus;
import pt.com.xml.EndPointReference;
import pt.com.xml.Notification;
import pt.com.xml.Notify;
import pt.com.xml.Poll;
import pt.com.xml.Publish;
import pt.com.xml.SoapEnvelope;
import pt.com.xml.SoapFault;
import pt.com.xml.SoapHeader;
import pt.com.xml.Status;
import pt.com.xml.Unsubscribe;

public class Builder
{
	private static final Charset CHARSET = Charset.forName("UTF-8");
	
	protected static final NetMessage soapToNetMessage(SoapEnvelope msg)
	{
		NetMessage message = null;

		String actionId = null;

		if (msg.body.notification != null)
		{
			Notification notification = msg.body.notification;

			String dest = notification.brokerMessage.destinationName;
			NetAction.DestinationType destType = NetAction.DestinationType.valueOf(msg.header.wsaFrom.address);
			NetBrokerMessage brkMsg = new NetBrokerMessage(notification.brokerMessage.textPayload.getBytes(CHARSET));
			String subs = msg.header.wsaTo;
			NetNotification netNotification = new NetNotification(dest, destType, brkMsg, subs);
			NetAction netAction = new NetAction(NetAction.ActionType.NOTIFICATION);

			message = new NetMessage(netAction);
			message.getAction().setNotificationMessage(netNotification);

		}
		else if (msg.body.publish != null)
		{
			BrokerMessage xmsg = msg.body.publish.brokerMessage;
			actionId = msg.body.publish.actionId;

			NetAction netAction = new NetAction(NetAction.ActionType.PUBLISH);
			NetAction.DestinationType dtype = NetAction.DestinationType.TOPIC;

			NetBrokerMessage netBkMsg = new NetBrokerMessage(xmsg.textPayload.getBytes(CHARSET));
			netBkMsg.setMessageId(xmsg.messageId);

			NetPublish netPublish = new NetPublish(xmsg.destinationName, dtype, netBkMsg);
			netPublish.setActionId(actionId);

			message = new NetMessage(netAction);
			message.getAction().setPublishMessage(netPublish);

		}
		else if (msg.body.enqueue != null)
		{
			BrokerMessage xmsg = msg.body.publish.brokerMessage;
			actionId = msg.body.publish.actionId;

			NetAction netAction = new NetAction(NetAction.ActionType.PUBLISH);
			NetAction.DestinationType dtype = NetAction.DestinationType.QUEUE;

			NetBrokerMessage netBkMsg = new NetBrokerMessage(xmsg.textPayload.getBytes(CHARSET));
			netBkMsg.setMessageId(xmsg.messageId);

			NetPublish netPublish = new NetPublish(xmsg.destinationName, dtype, netBkMsg);
			netPublish.setActionId(actionId);

			message = new NetMessage(netAction);
			message.getAction().setPublishMessage(netPublish);
		}
		else if (msg.body.notify != null)
		{
			Notify sb = msg.body.notify;
			actionId = sb.actionId;

			NetAction netAction = new NetAction(NetAction.ActionType.SUBSCRIBE);
			NetAction.DestinationType dtype = NetAction.DestinationType.valueOf(sb.destinationType);
			NetSubscribe netSubscribe = new NetSubscribe(sb.destinationName, dtype);
			netSubscribe.setActionId(actionId);

			message = new NetMessage(netAction);
			message.getAction().setSubscribeMessage(netSubscribe);

		}
		else if (msg.body.poll != null)
		{
			actionId = msg.body.poll.actionId;
			Poll poll = msg.body.poll;

			NetAction netAction = new NetAction(NetAction.ActionType.POLL);
			NetPoll netPoll = new NetPoll(poll.destinationName);
			netPoll.setActionId(actionId);

			message = new NetMessage(netAction);
			message.getAction().setPollMessage(netPoll);

		}
		else if (msg.body.acknowledge != null)
		{
			actionId = msg.body.acknowledge.actionId;
			Acknowledge ack = msg.body.acknowledge;

			NetAction netAction = new NetAction(NetAction.ActionType.ACKNOWLEDGE_MESSAGE);
			NetAcknowledgeMessage netAck = new NetAcknowledgeMessage(ack.destinationName, ack.messageId);
			netAck.setActionId(actionId);

			message = new NetMessage(netAction);
			message.getAction().setAcknowledgeMessage(netAck);
		}
		else if (msg.body.accepted != null)
		{
			actionId = msg.body.accepted.actionId;
			NetAction netAction = new NetAction(NetAction.ActionType.ACCEPTED);
			NetAccepted netAccepted = new NetAccepted(actionId);
			message = new NetMessage(netAction);
			message.getAction().setAcceptedMessage(netAccepted);
		}
		else if (msg.body.unsubscribe != null)
		{
			Unsubscribe unsubs = msg.body.unsubscribe;
			actionId = msg.body.unsubscribe.actionId;

			NetAction netAction = new NetAction(NetAction.ActionType.UNSUBSCRIBE);
			NetAction.DestinationType dtype = NetAction.DestinationType.valueOf(unsubs.destinationType);
			NetUnsubscribe netUnsubscribe = new NetUnsubscribe(unsubs.destinationName, dtype);
			netUnsubscribe.setActionId(actionId);

			message = new NetMessage(netAction);
			message.getAction().setUnsbuscribeMessage(netUnsubscribe);
		}
		else if (msg.body.checkStatus != null)
		{
			NetAction netAction = new NetAction(NetAction.ActionType.PING);
			NetPing netPing = new NetPing(System.currentTimeMillis());
			message = new NetMessage(netAction);
			message.getAction().setPingMessage(netPing);
		}
		else if (msg.body.status != null)
		{
			NetAction netAction = new NetAction(NetAction.ActionType.PONG);
			NetPong netPong = new NetPong(System.currentTimeMillis());
			message = new NetMessage(netAction);
			message.getAction().setPongMessage(netPong);
		}
		else
		{
			throw new RuntimeException("Not a valid request");
		}
		return message;
	}
	
	
	protected static final SoapEnvelope netMessageToSoap(NetMessage message)
	{
		NetMessage gcsMessage = (NetMessage) message;

		SoapEnvelope soap = new SoapEnvelope();

		switch (gcsMessage.getAction().getActionType())
		{
		case ACCEPTED:
			Accepted a = new Accepted();
			a.actionId = gcsMessage.getAction().getAcceptedMessage().getActionId();
			soap.body.accepted = a;
			break;
		case ACKNOWLEDGE_MESSAGE:
			Acknowledge ack = new Acknowledge();
			NetAcknowledgeMessage nack = gcsMessage.getAction().getAcknowledgeMessage();
			ack.actionId = nack.getActionId();
			ack.destinationName = nack.getDestination();
			ack.messageId = nack.getMessageId();
			soap.body.acknowledge = ack;
			break;
		case FAULT:
			SoapFault f = new SoapFault();
			NetFault nf = gcsMessage.getAction().getFaultMessage();
			f.faultCode.value = nf.getCode();
			f.faultReason.text = nf.getMessage();
			f.detail = nf.getDetail();
			soap.body.fault = f;
			break;
		case NOTIFICATION:
			Notification notf = new Notification();
			NetNotification nnotf = gcsMessage.getAction().getNotificationMessage();
			notf.brokerMessage = buildXmlBrokerMessage(nnotf.getMessage(), nnotf.getDestination());

			SoapEnvelope soap_env = new SoapEnvelope();
			SoapHeader soap_header = new SoapHeader();
			EndPointReference epr = new EndPointReference();
			epr.address = nnotf.getDestinationType().toString();
			soap_header.wsaFrom = epr;
			if (nnotf.getSubscription() != null)
			{
				soap_header.wsaTo = nnotf.getSubscription();
			}

			soap_header.wsaMessageID = "http://services.sapo.pt/broker/message/" + notf.brokerMessage.messageId;
			soap_header.wsaAction = "http://services.sapo.pt/broker/notification/";
			soap_env.header = soap_header;
			soap.header = soap_header;
			soap.body.notification = notf;
			break;
		case POLL:
			Poll poll = new Poll();
			NetPoll npoll = gcsMessage.getAction().getPollMessage();
			poll.actionId = npoll.getActionId();
			poll.destinationName = npoll.getDestination();
			soap.body.poll = poll;
			break;
		case PUBLISH:
			Publish pub = new Publish();
			NetPublish npub = gcsMessage.getAction().getPublishMessage();
			pub.actionId = npub.getActionId();
			pub.brokerMessage = buildXmlBrokerMessage(npub.getMessage(), npub.getDestination());
			soap.body.publish = pub;
			break;
		case SUBSCRIBE:
			Notify notify = new Notify();
			NetSubscribe nsubs = gcsMessage.getAction().getSubscribeMessage();
			notify.actionId = nsubs.getActionId();
			notify.destinationName = nsubs.getDestination();
			notify.destinationType = nsubs.getDestinationType().toString();
			soap.body.notify = notify;
			break;
		case UNSUBSCRIBE:
			Unsubscribe unsubscribe = new Unsubscribe();
			NetUnsubscribe nunsubscribe = gcsMessage.getAction().getUnsbuscribeMessage();
			unsubscribe.actionId = nunsubscribe.getActionId();
			unsubscribe.destinationName = nunsubscribe.getDestination();
			unsubscribe.destinationType = nunsubscribe.getDestinationType().toString();
			soap.body.unsubscribe = unsubscribe;
			break;
		case PING:
			soap.body.checkStatus = new CheckStatus();
			break;
		case PONG:
			Status status = new Status();
			long ts = gcsMessage.getAction().getPongMessage().getTimestamp();
			if (ts > 0)
			{
				status.timestamp = DateUtil.formatISODate(new Date(ts));
			}
			soap.body.status = status;
			break;
		}

		return soap;
	}

	private static final BrokerMessage buildXmlBrokerMessage(NetBrokerMessage net_bkmsg, String destinationName)
	{
		BrokerMessage bkmsg = new BrokerMessage();
		bkmsg.destinationName = destinationName;
		bkmsg.messageId = net_bkmsg.getMessageId();
		bkmsg.textPayload = new String(net_bkmsg.getPayload(), CHARSET);

		if (net_bkmsg.getExpiration() > 0)
		{
			bkmsg.expiration = DateUtil.formatISODate(new Date(net_bkmsg.getExpiration()));
		}

		if (net_bkmsg.getTimestamp() > 0)
		{
			bkmsg.timestamp = DateUtil.formatISODate(new Date(net_bkmsg.getTimestamp()));
		}

		return bkmsg;
	}

}
