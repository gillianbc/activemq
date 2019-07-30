package org.gillianbc;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

public class MsgListenerImpl implements MessageListener {
	private int textMsgCount = 0;
	private int objMsgCount = 0;
	
	public void onMessage(Message message)
    {
        try
        {
            if (message instanceof TextMessage)
            {
                processTextMessage(message);
            } else if (message instanceof ObjectMessage){
            	processObjectMessage(message);
            }
            else
            {
                processInvalidMessage();
            }
        }
        catch (JMSException e)
        {
            System.out.println("Caught:" + e);
            e.printStackTrace();
        }
    }

	private void processInvalidMessage() {
		System.out.println("Invalid message received.");
	}

	private void processTextMessage(Message message) throws JMSException {
		TextMessage txtMessage = (TextMessage)message;
		
		textMsgCount++;
		
		System.out.println("Test Message received: " + textMsgCount + " " + txtMessage.getText());
	}
	private void processObjectMessage(Message message) throws JMSException {
		ObjectMessage objMessage = (ObjectMessage)message;
		System.out.println("Test Object Message received: ");
		objMsgCount++;
	}
	public int getMsgCount() {
		return textMsgCount;
	}

	
}
