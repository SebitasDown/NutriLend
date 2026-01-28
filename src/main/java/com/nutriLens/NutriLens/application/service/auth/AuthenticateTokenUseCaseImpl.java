package com.nutriLens.NutriLens.application.service.auth;

import com.nutriLens.NutriLens.application.exception.InvalidCredential;
import com.nutriLens.NutriLens.domain.port.in.auth.AuthenticateTokenUseCase;
import com.nutriLens.NutriLens.domain.port.in.auth.UserSession;
import com.nutriLens.NutriLens.domain.port.out.TokenValidator;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateTokenUseCaseImpl implements AuthenticateTokenUseCase {

    private final TokenValidator tokenValidator;

    public AuthenticateTokenUseCaseImpl(TokenValidator tokenValidator) {
        this.tokenValidator = tokenValidator;
    }

    @Override
    public UserSession execute(String token) {
        if (token == null || token.isBlank()) {
            throw new InvalidCredential("Token vacio");
        }
        if (!tokenValidator.isValid(token)) {
            throw new InvalidCredential("Token invalido");
        }
        Long userId = tokenValidator.extractUserId(token)
                .orElseThrow(() -> new InvalidCredential("Token inválido"));

        String email = tokenValidator.extractEmail(token)
                .orElse("unknown");

        return new UserSession(userId, email);
    }
}
