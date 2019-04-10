package com.wan.cont;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "com.wan")
public class ControllerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ControllerApplication.class);

        if(run.isActive()){
            System.out.println("Controller Start Success");
        }else {
            System.out.println("Controller Start Fail");
        }
    }
}
