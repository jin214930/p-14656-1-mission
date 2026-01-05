package com.back.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class BaseInitData {
    @Bean
    public ApplicationRunner baseInitDataRunner() {
        return args -> {
            log.debug("ApplicationRunner 실행");
        };
    }
}
