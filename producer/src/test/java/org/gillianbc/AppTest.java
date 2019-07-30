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
import org.junit.After;
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
    private Consumer consumer;
    private Producer producer;
    private MsgListenerImpl mlistener;
    private final static String QUEUE_NAME = "TestGBC";
    
    
    @Before
    public void setup(){
    	System.out.println("BEFORE");
    	// setup the connection to ActiveMQ
        factory  = new ActiveMQConnectionFactory(brokerURL);
        
        mlistener = new MsgListenerImpl();
        try {
        	producer = new Producer(factory, QUEUE_NAME);
			consumer = new Consumer(mlistener);
        	/*connection = factory.createConnection();
			connection.start();
	        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	        
	        Destination destination = session.createQueue(QUEUE_NAME);
            consumer = session.createConsumer(destination);
            consumer.setMessageListener(mlistener);*/
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
    /*
     * Tests that 3 text messages are created and consumed
     */
    @Test
    public void testTextMsgs(){
		try {
			
			producer.run(3, "Hello vvv");
	        producer.close();
	        consumer.run(QUEUE_NAME);
	        System.out.println("Count is " + mlistener.getMsgCount());
            assertEquals(3, mlistener.getMsgCount());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /*
     * Tests that 3 text messages are created and consumed
     */
    @Test
    public void testObjectMsgs() throws JMSException{
    	
    	producer.run(3, "Hello");
        assertEquals(3, mlistener.getMsgCount());
    }
    
    @After
    public void tearDown(){
    	try {
			producer.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
