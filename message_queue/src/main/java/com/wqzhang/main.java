//package com.wqzhang;
//
//import com.wqzhang.producer.MessageProducer;
//import org.springframework.boot.SpringApplication;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
///**
// * @author wqzhang
// * @version 1.0.0
// * @ClassName main
// * @Description ${todo}
// * @Date 2018/1/26 14:38
// */
//public class main {
//    public static void main(String[] args) {
//        ApplicationContext cxt = new ClassPathXmlApplicationContext("classpath:queueApplication.xml");
//        MessageProducer messageProducer = (MessageProducer) cxt.getBean("messageProducer");
//        for (int i = 0; i < 100; i++) {
//            PersonInfo personInfo = new PersonInfo();
//            personInfo.setUserName(i + "");
//            messageProducer.sendMessage(personInfo);
//        }
//
//    }
//}
