package com.wqzhang;

import com.wqzhang.bean.PersonInfo;
import com.wqzhang.producer.QueueMessageProducer;
import com.wqzhang.producer.TopicMessageProducer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wqzhang
 * @version 1.0.0
 * @ClassName main
 * @Description ${todo}
 * @Date 2018/1/26 14:38
 */
public class main {
    public static void main(String[] args) {
        ApplicationContext cxt = new ClassPathXmlApplicationContext("classpath:springApplication.xml");
        QueueMessageProducer queueMessageProducer = (QueueMessageProducer) cxt.getBean("queueMessageProducer");
        for (int i = 0; i < 100; i++) {
            PersonInfo personInfo = new PersonInfo();
            personInfo.setUserName(i + "");
            queueMessageProducer.sendMessage(personInfo);
        }

        TopicMessageProducer topicMessageProducer = (TopicMessageProducer) cxt.getBean("topicMessageProducer");
        for (int i = 0; i < 100; i++) {
            PersonInfo personInfo = new PersonInfo();
            personInfo.setUserName(i + "");
            topicMessageProducer.sendMessage(personInfo);
        }


    }
}
