package com.wqzhang.producer;

import com.wqzhang.bean.PersonInfo;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Queue;

/**
 * @author wqzhang
 * @version 1.0.0
 * @ClassName MessageProducer
 * @Description ${todo}
 * @Date 2018/1/26 13:47
 */
@Component(value = "messageProducer")
public class MessageProducer {

    public MessageProducer() {
        System.out.println("MessageProducer init");
    }

    @Resource(name = "queueJmsTemplate")
    private JmsTemplate jmsTemplate;

    @Resource(name = "queueDestination")
    private Queue defaultDestination;

    public void sendMessage(PersonInfo personInfo) {
        System.out.println("MessageProducer 开始发送消息");
        jmsTemplate.convertAndSend(defaultDestination, personInfo);
    }
}
