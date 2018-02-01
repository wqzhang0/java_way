package com.wqzhang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author wqzhang
 * @version 1.0.0
 * @ClassName Application
 * @Description ${todo}
 * @Date 2018/1/29 15:54
 */
@SpringBootApplication
@ImportResource(locations = {"classpath:springApplication.xml"})
public class Application {// 非web项目保持长连
    private static volatile boolean running = true;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        System.out.print("============================ Spring boot Active MQ 启动成功 ============================");

        synchronized (Application.class) {
            while (running) {
                try {
                    Application.class.wait();
                } catch (Throwable e) {
                }
            }
        }

    }
}
