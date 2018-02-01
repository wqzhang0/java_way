package com.wqzhang.receiver;

import com.wqzhang.bean.PersonInfo;
import org.apache.activemq.command.ActiveMQObjectMessage;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * @author wqzhang
 * @version 1.0.0
 * @ClassName TopicConsumerA
 * @Description ${todo}
 * @Date 2018/1/26 14:01
 */
public class TopicConsumerA {
    public void reciver(PersonInfo personInfo) throws JMSException {
        System.out.println("TopicConsumerA 收到信息");
        System.out.println(personInfo.toString());
    }
}
