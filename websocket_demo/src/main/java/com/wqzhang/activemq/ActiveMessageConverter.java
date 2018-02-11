package com.wqzhang.activemq;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

/**
 * @author wqzhang
 * @version 1.0.0
 * @ClassName ActiveMessageConverter
 * @Description 即时消息转换器
 * @Date 2018/1/26 13:44
 */
@Component("activeMessageConverter")
public class ActiveMessageConverter implements MessageConverter {
    @Override
    public Message toMessage(Object o, Session session) throws JMSException,
            MessageConversionException {
        if (o instanceof MessageBean) {
            ActiveMQObjectMessage objectMessage = (ActiveMQObjectMessage) session
                    .createObjectMessage();
            objectMessage.setObject((MessageBean) o);
            return objectMessage;
        }
        return null;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException,
            MessageConversionException {
        if (message instanceof ObjectMessage) {
            ObjectMessage objectMessage = (ObjectMessage) message;
            if (objectMessage instanceof ActiveMQObjectMessage) {
                MessageBean messageBean = (MessageBean) objectMessage
                        .getObject();
                return messageBean;
            }
        }
        return null;
    }
}
