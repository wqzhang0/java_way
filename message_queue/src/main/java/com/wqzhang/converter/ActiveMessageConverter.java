package com.wqzhang.converter;


import com.wqzhang.bean.PersonInfo;
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
 * @ClassName aa
 * @Description ${todo}
 * @Date 2018/1/26 13:44
 */
@Component
public class ActiveMessageConverter implements MessageConverter {
    @Override
    public Message toMessage(Object o, Session session) throws JMSException, MessageConversionException {
        System.out.println("ActiveMessageConverter  toMessage");
        if (o instanceof PersonInfo) {
            ActiveMQObjectMessage objectMessage = (ActiveMQObjectMessage) session.createObjectMessage();
            objectMessage.setObject((PersonInfo) o);
            return objectMessage;
        } else {

        }
        return null;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        System.out.println("ActiveMessageConverter fromMessage");
        if (message instanceof ObjectMessage) {
            ObjectMessage objectMessage = (ObjectMessage) message;
            if (objectMessage instanceof ActiveMQObjectMessage) {
                ActiveMQObjectMessage activeMQObjectMessage = (ActiveMQObjectMessage) objectMessage;
                if (activeMQObjectMessage.getObject() instanceof PersonInfo) {

                    PersonInfo personInfo = (PersonInfo) activeMQObjectMessage.getObject();
                    System.out.println(personInfo.toString());
                    return personInfo;
                }
            }
        }
        return null;
    }
}
