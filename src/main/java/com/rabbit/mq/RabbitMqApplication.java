package com.rabbit.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author: zhoujiong
 * @description: 启动类
 * @className: RabbitMqApplication
 * @date: 2019/4/2 15:53
 */
@SpringBootApplication
public class RabbitMqApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(RabbitMqApplication.class, args);
		if(run.isActive()){
			System.out.println("start success");
		}else {
			System.out.println("start fail");
		}

	}

}
