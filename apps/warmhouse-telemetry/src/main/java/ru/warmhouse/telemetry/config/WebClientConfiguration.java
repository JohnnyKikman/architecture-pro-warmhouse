package ru.warmhouse.telemetry.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Bean
    public WebClient sensorWebClient() {
        return WebClient.builder()
                .baseUrl("http://app:8080/api/v1")
                .build();
    }
}
