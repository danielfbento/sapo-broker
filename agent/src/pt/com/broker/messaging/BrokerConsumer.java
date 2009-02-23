package pt.com.broker.messaging;

import org.apache.mina.core.session.IoSession;
import org.caudexorigo.text.StringUtils;

import pt.com.types.NetSubscribe;
import pt.com.types.NetUnsubscribe;
import pt.com.types.NetAction.DestinationType;

public class BrokerConsumer
{
	private static BrokerConsumer instance = new BrokerConsumer();

	public static BrokerConsumer getInstance()
	{
		return instance;
	}

	private BrokerConsumer()
	{
	}

	public void listen(NetSubscribe subscription, IoSession ios)
	{
		try
		{
			QueueSessionListener qsl = QueueSessionListenerList.get(subscription.getDestination());
			qsl.addConsumer(ios);
		}
		catch (Throwable e)
		{
			throw new RuntimeException(e);
		}
	}

	public void subscribe(NetSubscribe sb, IoSession ios)
	{
		if (StringUtils.contains(sb.getDestination(), "@"))
		{
			throw new IllegalArgumentException("'@' character not allowed in TOPIC name");
		}

		try
		{
			TopicSubscriber subscriber = TopicSubscriberList.get(sb.getDestination());
			subscriber.addConsumer(ios);
		}
		catch (Throwable e)
		{
			if (e instanceof InterruptedException)
			{
				Thread.currentThread().interrupt();
			}
			throw new RuntimeException(e);
		}
	}

	/* TODO: answer the question - Why the synchronized? */
	public synchronized void unsubscribe(NetUnsubscribe unsubs, IoSession session)
	{
		String dname = unsubs.getDestination();
		DestinationType dtype = unsubs.getDestinationType();
		if (dtype == DestinationType.TOPIC)
		{
			TopicSubscriber subscriber = TopicSubscriberList.get(dname);
			subscriber.removeSessionConsumer(session);
		}
		else if (dtype == DestinationType.QUEUE)
		{
			QueueSessionListener qsl = QueueSessionListenerList.get(dname);
			qsl.removeSessionConsumer(session);
		}
	}
}