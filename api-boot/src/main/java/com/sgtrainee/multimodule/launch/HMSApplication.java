package com.sgtrainee.multimodule.launch;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sgtrainee.multimodule"})
@EntityScan(basePackages = {"com.sgtrainee.multimodule"})
@EnableJpaRepositories(basePackages = {"com.sgtrainee.multimodule"})
public class HMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(HMSApplication.class, args);

    }
}
