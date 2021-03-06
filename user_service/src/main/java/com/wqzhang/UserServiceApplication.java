package com.wqzhang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"classpath:dubbo-provider.xml"})
public class UserServiceApplication {
	// 非web项目保持长连
	private static volatile boolean running = true;


	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);

		System.out.print("============================ 用户模块Dubbo服务启动成功 ============================");

		synchronized (UserServiceApplication.class) {
			while (running) {
				try {
					UserServiceApplication.class.wait();
				} catch (Throwable e) {
				}
			}
		}
	}

}
