package com.nutriLens.NutriLens.domain.port.in.auth;

public interface AuthenticateTokenUseCase {
    UserSession execute(String token);
}
