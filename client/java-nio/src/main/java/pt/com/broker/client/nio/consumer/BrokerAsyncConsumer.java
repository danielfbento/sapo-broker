package pt.com.broker.client.nio.consumer;

import io.netty.channel.Channel;
import pt.com.broker.client.nio.events.BrokerListener;
import pt.com.broker.client.nio.events.BrokerListenerAdapter;
import pt.com.broker.types.*;

/**
 * Created by luissantos on 22-04-2014.
 */
public class BrokerAsyncConsumer {


    private final NetAction.DestinationType destinationType;

    private final String destinationName;

    private final BrokerListener listener;


    public BrokerAsyncConsumer(String destinationName, NetAction.DestinationType destinationType,  BrokerListener listener)
    {

        this.destinationType = destinationType;
        this.listener = listener;
        this.destinationName = destinationName;



    }

    public boolean deliver(NetMessage msg,Channel channel) throws Throwable {

        listener.deliverMessage(msg,channel);

        return true;
    }

    public NetAction.DestinationType getDestinationType() {
        return destinationType;
    }

    public String getDestinationName() {
        return destinationName;
    }



}
