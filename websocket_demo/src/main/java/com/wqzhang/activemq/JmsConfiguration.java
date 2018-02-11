package com.wqzhang.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @author wqzhang
 * @version 1.0.0
 * @ClassName JmsConfiguration
 * @Description jms消息配置
 * @Date 2018年2月7日
 */
@Configuration
@EnableJms
public class JmsConfiguration {

    @Autowired
    ActiveMessageConverter activeMessageConverter;

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("biz.queue");
    }

    @Bean
    public Topic topic() {
        return new ActiveMQTopic("biz.topic");
    }

    /* *//** * 消息转换器 * @return */
    /*
     * @Bean public MessageConverter jacksonJmsMessageConverter() { return
     * activeMessageConverter; }
     */

    /**
     * topic模式的ListenerContainer
     *
     * @param activeMQConnectionFactory
     */
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(
            ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(activeMQConnectionFactory);
        bean.setMessageConverter(activeMessageConverter);
        bean.setPubSubDomain(true);
        return bean;
    }

    /**
     * queue模式的ListenerContainer
     *
     * @param activeMQConnectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(
            ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(activeMQConnectionFactory);
        /** * 使用消息转换器 */
        bean.setMessageConverter(activeMessageConverter);
        return bean;
    }

    @Bean
    public MessageConverter messageConverter() {
        return activeMessageConverter;
    }

}
