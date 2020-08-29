package com.agileengine.imagegallery.config;

import com.agileengine.imagegallery.service.impl.RestTemplateAgileEngineAuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Value("${agileengine.api.endpoint}")
    private String endpoint;

    @Value("${agileengine.api.key}")
    private String apiKey;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().rootUri(endpoint).build();
    }

    @Bean
    public RestTemplateAgileEngineAuthService.AuthRequest getAuthRequest() {
        return new RestTemplateAgileEngineAuthService.AuthRequest(apiKey);
    }
}
