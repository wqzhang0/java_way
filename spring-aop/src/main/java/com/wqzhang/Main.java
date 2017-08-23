package com.wqzhang;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by com.wqzhang on 2017/7/6.
 */
//@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
//        Boss boss = new Boss();

        com.wqzhang.Boss boss = (com.wqzhang.Boss) context.getBean("boss");
        boss.bookFlight(true);
        boss.bookFlight(false);
    }
}
