package com.wqzhang.receiver;

import com.wqzhang.bean.PersonInfo;

import javax.jms.JMSException;
import javax.jms.MessageListener;

/**
 * @author wqzhang
 * @version 1.0.0
 * @ClassName TopicConsumerC
 * @Description ${todo}
 * @Date 2018/1/26 14:01
 */
public class TopicConsumerC {

    public void reciver(PersonInfo personInfo) throws JMSException {
        System.out.println("TopicConsumerC 收到信息");
        System.out.println(personInfo.toString());
    }

}
