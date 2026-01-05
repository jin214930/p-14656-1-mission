package com.back.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.elasticsearch.config.EnableElasticsearchAuditing;

import java.time.OffsetDateTime;
import java.util.Optional;

@Configuration
@EnableElasticsearchAuditing
public class AuditingConfig {
    @Bean
    public DateTimeProvider dataTimeProvider() {
        return () -> Optional.of(OffsetDateTime.now());
    }
}
