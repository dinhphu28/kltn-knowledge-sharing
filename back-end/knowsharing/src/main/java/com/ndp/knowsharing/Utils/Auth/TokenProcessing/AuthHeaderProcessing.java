package com.ndp.knowsharing.Utils.Auth.TokenProcessing;

import org.springframework.stereotype.Component;

@Component
public class AuthHeaderProcessing {
    public String getTokenFromAuthHeader(String authorization) {
        String token = authorization.substring(7);

        return token;
    }
}