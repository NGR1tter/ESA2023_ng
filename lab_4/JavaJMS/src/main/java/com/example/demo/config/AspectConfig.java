package com.example.demo.config;

import com.example.demo.jms.LoggingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AspectConfig {
    @Bean
    public LoggingAspect databaseChangeAspect() {
        return new LoggingAspect();
    }
}
