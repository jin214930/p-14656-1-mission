package com.back.global;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseInitData {
    @Bean
    public ApplicationRunner baseInitDataRunner() {
        return args -> {
            System.out.println("ApplicationRunner 실행");
        };
    }
}
