package com.example.webflux.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi customApi() {
        return GroupedOpenApi.builder()
            .group("Custom")
            .packagesToScan("com.example.webflux.router")
            .build();
    }

}
