package com.nutriLens.NutriLens.infrastructure.security;

import com.nutriLens.NutriLens.domain.model.User;
import com.nutriLens.NutriLens.domain.port.out.TokenProvider;
import lombok.RequiredArgsConstructor;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

// JWT: Generador de token
@Component
@RequiredArgsConstructor
public class JwtTokenProvider implements TokenProvider {

    private final JwProperties properties;

    @Override
    public String generateAccessToken(User user) {
        return createToken(user, properties.getAccessTokenExpirationMs());
    }

    @Override
    public String generateRefreshToken(User user) {
        return createToken(user, properties.getRefreshTokenExpirationMs());
    }

    private String createToken(User user, long expirationMs) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationMs);
        Key key = Keys.hmacShaKeyFor(properties.getSecret().getBytes());

        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .claim("id", user.getId())
                .claim("email", user.getEmail())
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }
}
