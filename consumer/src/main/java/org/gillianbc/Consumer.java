package org.gillianbc;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer {
	public static String brokerURL = "tcp://localhost:61616";

    private Connection connection;
    private Session session;
    private MessageConsumer consumer;
    private MsgListenerImpl msgListener;
    
    public Consumer(MsgListenerImpl msgListener){
    	this.msgListener = msgListener;
    }
    public void run(String queueName)
    {
        try
        {
            ConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(queueName);
            consumer = session.createConsumer(destination);
            consumer.setMessageListener(msgListener);
        }
        catch (Exception e)
        {
            System.out.println("Caught:" + e);
            e.printStackTrace();
        }
    }
}
