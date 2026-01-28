package com.nutriLens.NutriLens.domain.port.in.auth;

public interface RegisterUserUseCase {
    AuthResult register(String displayName, String email, String password);
}
