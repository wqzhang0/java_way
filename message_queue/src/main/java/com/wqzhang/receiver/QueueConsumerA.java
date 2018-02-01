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
public class QueueConsumerA implements MessageListener {
    public void onMessage(Message message) {
        //收到消息
//        System.out.println(" QueueConsumerA 收到消息");

        ActiveMQObjectMessage activeMQObjectMessage = (ActiveMQObjectMessage) message;
        try {
            if (activeMQObjectMessage.getObject() instanceof PersonInfo) {

                PersonInfo personInfo = (PersonInfo) activeMQObjectMessage.getObject();
                System.out.println("QueueConsumerA  " + personInfo.getUserName() + "Thread ID |" + Thread.currentThread().getId());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }

//        System.out.println(message.toString());
    }
}
