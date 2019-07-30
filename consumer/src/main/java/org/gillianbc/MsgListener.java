package org.gillianbc;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MsgListener implements MessageListener {
	private int textMsgCount = 0;
	
	public void onMessage(Message message)
    {
        try
        {
            if (message instanceof TextMessage)
            {
                processTextMessage(message);
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
		System.out.println("Test Message received: " + txtMessage.getText());
		textMsgCount++;
	}

	public int getMsgCount() {
		return textMsgCount;
	}

	
}
