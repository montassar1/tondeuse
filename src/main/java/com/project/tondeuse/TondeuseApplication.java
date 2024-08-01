package com.project.tondeuse;


import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;

@SpringBootApplication(exclude = {PropertyPlaceholderAutoConfiguration.class})
@EnableBatchProcessing
public class TondeuseApplication {

    public static void main(String[] args) {
        SpringApplication.run(TondeuseApplication.class, args);
    }
}
