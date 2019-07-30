package org.gillianbc;

public class App 
{

    public static String brokerURL = "tcp://localhost:61616";


    public static void main( String[] args )
    {
        Consumer consumer = new Consumer(new MsgListenerImpl());
        consumer.run("testQueue");
    }


}
