package com.wan.pub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author: zhoujiong
 * @description: 生产者
 * @className: RabbitMQPublisherApplication
 * @date: 2019/4/3 17:30
 */
@SpringBootApplication
public class RabbitMQPublisherApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(RabbitMQPublisherApplication.class);

        if(run.isActive()){
            System.out.println("RabbitMQPublisher Start Success");
        }else {
            System.out.println("RabbitMQPublisher Start Fail");
        }
    }
}
