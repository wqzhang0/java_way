package com.wqzhang.producer;

import com.wqzhang.bean.PersonInfo;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Queue;

/**
 * @author wqzhang
 * @version 1.0.0
 * @ClassName TopicMessageProducer
 * @Description ${todo}
 * @Date 2018/1/26 13:47
 */
@Component(value = "topicMessageProducer")
public class TopicMessageProducer {

    public TopicMessageProducer() {
        System.out.println("topicMessageProducer init");
    }

    @Resource(name = "topicJmsTemplate")
    private JmsTemplate jmsTemplate;


    public void sendMessage(PersonInfo personInfo) {
        System.out.println("TopicMessageProducer 开始发送消息");
        jmsTemplate.convertAndSend(personInfo);
    }
}
