package com.wan.ser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "com.wan")
public class ServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ServerApplication.class);

        if(run.isActive()){
            System.out.println("Server Start Success");
        }else {
            System.out.println("Server Start Fail");
        }
    }

}
