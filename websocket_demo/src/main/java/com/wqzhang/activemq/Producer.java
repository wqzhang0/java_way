package com.wqzhang.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;
import javax.jms.Topic;
import java.io.Serializable;

/**
 * @author wqzhang
 * @version 1.0.0
 * @ClassName Producer
 * @Description 消息生产者, 通过spring factory 获取使用
 * @Date 2018年2月7日
 */
@Service("messageProducer")
public class Producer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Topic topic;

    @Autowired
    private Queue queue;

    public void send(final Serializable msgBean) {

        jmsMessagingTemplate.convertAndSend(topic, msgBean);
    }

}
