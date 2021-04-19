package com.accolite.au;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AuApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuApplication.class, args);
    }
}