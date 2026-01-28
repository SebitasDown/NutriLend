package com.nutriLens.NutriLens.infrastructure.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwProperties {

    private String secret;
    private long accessTokenExpirationMs;
    private long refreshTokenExpirationMs;

    public JwProperties() {
    }

    public String getSecret() {
        return secret;
    }

    public long getAccessTokenExpirationMs() {
        return accessTokenExpirationMs;
    }

    public long getRefreshTokenExpirationMs() {
        return refreshTokenExpirationMs;
    }
}