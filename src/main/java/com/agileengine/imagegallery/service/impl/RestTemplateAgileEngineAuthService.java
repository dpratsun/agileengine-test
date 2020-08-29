package com.agileengine.imagegallery.service.impl;

import com.agileengine.imagegallery.service.AgileEngineAuthService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@AllArgsConstructor
@Service
public class RestTemplateAgileEngineAuthService implements AgileEngineAuthService {
    private final static String AUTH_ENDPOINT = "/auth";

    private final RestTemplate restTemplate;
    private final AuthRequest authRequest;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthRequest {
        private String apiKey;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthResponse {
        private boolean auth;
        private String token;
    }

    @Override
    public String getToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AuthRequest> request = new HttpEntity<>(authRequest, headers);
        ResponseEntity<AuthResponse> response = restTemplate.postForEntity(AUTH_ENDPOINT, request, AuthResponse.class);
        return Objects.requireNonNull(response.getBody()).getToken();
    }
}
