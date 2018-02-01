package com.wqzhang.receiver;

import com.wqzhang.bean.PersonInfo;

import javax.jms.JMSException;

/**
 * @author wqzhang
 * @version 1.0.0
 * @ClassName QueueConsumerA
 * @Description ${todo}
 * @Date 2018/1/26 14:01
 */
public class QueueConsumerC {
    public void reciver(PersonInfo personInfo) throws JMSException{
        System.out.println("QueueConsumerC 收到信息");
        System.out.println(personInfo.toString());
    }
}
