package com.agileengine.imagegallery.service.impl;

import com.agileengine.imagegallery.service.AgileEngineAuthService;
import com.agileengine.imagegallery.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final AgileEngineAuthService service;
    private String token;

    @Override
    public String get() {
        return token;
    }

    @Override
    @PostConstruct
    public String renew() {
        token = service.getToken();

        return token;
    }
}
