package com.nutriLens.NutriLens.infrastructure.security;

import com.nutriLens.NutriLens.domain.port.out.TokenValidator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtTokenValidator implements TokenValidator {

    private final JwProperties properties;

    @Override
    public boolean isValid(String token) {
        try {
            Key key = Keys.hmacShaKeyFor(properties.getSecret().getBytes());
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<Long> extractUserId(String token) {
        try {
            Key key = Keys.hmacShaKeyFor(properties.getSecret().getBytes());
            Long id = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .get("id", Long.class);
            return Optional.ofNullable(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<String> extractEmail(String token) {
        try {
            Key key = Keys.hmacShaKeyFor(properties.getSecret().getBytes());
            String email = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return Optional.ofNullable(email);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
