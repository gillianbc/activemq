package org.gillianbc;


import static org.junit.Assert.*;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	private ConnectionFactory factory;
	public static String brokerURL = "tcp://localhost:61616";
	private Connection connection;
    private Session session;
    private MessageConsumer consumer;
    
    private MsgListener mlistener;
    
    private final static String QUEUE_NAME = "TestGBC";
    
    
    @Before
    public void setup(){
    	System.out.println("BEFORE");
    	// setup the connection to ActiveMQ
        factory  = new ActiveMQConnectionFactory(brokerURL);
        mlistener = new MsgListener();
        try {
			connection = factory.createConnection();
			connection.start();
	        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	        
	        Destination destination = session.createQueue(QUEUE_NAME);
            consumer = session.createConsumer(destination);
            consumer.setMessageListener(mlistener);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    @Test
    public void testApp()
    {
        assertTrue( true );
    }
    
    @Test
    public void testTextMsgs(){
    	Producer producer;
		try {
			producer = new Producer(factory, QUEUE_NAME);
			producer.run(3, "Hello");
	        producer.close();
	        System.out.println("Count is " + mlistener.getMsgCount());
            assertEquals(3, mlistener.getMsgCount());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
    private class MListener implements MessageListener {

    	public void onMessage(Message message)
        {
            try
            {
                if (message instanceof TextMessage)
                {
                    TextMessage txtMessage = (TextMessage)message;
                    System.out.println("Test Message received: " + txtMessage.getText());
                }
                else
                {
                    System.out.println("Invalid message received.");
                }
            }
            catch (JMSException e)
            {
                System.out.println("Caught:" + e);
                e.printStackTrace();
            }
        }
    	
    }
}
