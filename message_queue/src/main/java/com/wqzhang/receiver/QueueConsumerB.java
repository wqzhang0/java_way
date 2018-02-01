package com.wqzhang.receiver;

import com.wqzhang.bean.PersonInfo;
import org.apache.activemq.command.ActiveMQObjectMessage;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * @author wqzhang
 * @version 1.0.0
 * @ClassName QueueConsumerA
 * @Description ${todo}
 * @Date 2018/1/26 14:01
 */
public class QueueConsumerB implements MessageListener {
    public void onMessage(Message message) {
        ActiveMQObjectMessage activeMQObjectMessage = (ActiveMQObjectMessage) message;
        try {
            if (activeMQObjectMessage.getObject() instanceof PersonInfo) {

                PersonInfo personInfo = (PersonInfo) activeMQObjectMessage.getObject();

                System.out.println("QueueConsumerB  " + personInfo.getUserName() + "Thread ID |" + Thread.currentThread().getId());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
