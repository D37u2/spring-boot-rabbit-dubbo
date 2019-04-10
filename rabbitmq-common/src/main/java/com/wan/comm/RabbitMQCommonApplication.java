package com.wan.comm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RabbitMQCommonApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(RabbitMQCommonApplication.class);

        if(run.isActive()) {
            System.out.println("RabbitMQCommon Start Success");
        }
        else {
            System.out.println("RabbitMQCommon Start Fail");
        }
    }

}
