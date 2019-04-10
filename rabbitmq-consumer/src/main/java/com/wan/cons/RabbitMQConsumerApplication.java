package com.wan.cons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author: zhoujiong
 * @description: 消费者
 * @className: RabbitMQConsumerApplication
 * @date: 2019/4/3 17:30
 */
@SpringBootApplication(scanBasePackages = "com.wan")
public class RabbitMQConsumerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(RabbitMQConsumerApplication.class);

        if(run.isActive()){
            System.out.println("RabbitMQConsumer Start Success");
        }else {
            System.out.println("RabbitMQConsumer Start Fail");
        }
    }

}
