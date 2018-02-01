package com.wqzhang.topic;

import com.wqzhang.bean.PersonInfo;

import javax.jms.JMSException;

/**
 * @author wqzhang
 * @version 1.0.0
 * @ClassName TopicCunsumerA
 * @Description ${todo}
 * @Date 2018/1/26 14:05
 */
public class TopicCunsumerA {

    public void receiveA(PersonInfo personInfo) throws JMSException {
        System.out.println("【TopicConsumerA】 收到TopicProducer的消息—->personInfo的用户名是:" + personInfo.getUserName());
        System.out.println();
    }

}
