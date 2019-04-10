package com.wan.ent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class EntityApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(EntityApplication.class);

        if(run.isActive()){
            System.out.println("Entity Start Success");
        }else {
            System.out.println("Entity Start Fail");
        }
    }

}
