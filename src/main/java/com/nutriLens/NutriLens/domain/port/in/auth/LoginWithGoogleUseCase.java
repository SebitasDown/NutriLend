package com.nutriLens.NutriLens.domain.port.in.auth;

public interface LoginWithGoogleUseCase {
    AuthResult loginWithGoogle(String googleSub, String email, String name, String avatarUrl);
}
