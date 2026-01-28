package com.nutriLens.NutriLens.domain.port.in.auth;

public interface LoginUserUseCase {
    AuthResult login(String email, String password);
}
