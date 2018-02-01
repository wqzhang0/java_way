package com.wqzhang.receiver;

import com.wqzhang.bean.PersonInfo;

import javax.jms.JMSException;

/**
 * @author wqzhang
 * @version 1.0.0
 * @ClassName TopicConsumerB
 * @Description ${todo}
 * @Date 2018/1/26 14:01
 */
public class TopicConsumerB {
    public void reciver(PersonInfo personInfo) throws JMSException {
        System.out.println("TopicConsumerB 收到信息");
        System.out.println(personInfo.toString());
    }

}
