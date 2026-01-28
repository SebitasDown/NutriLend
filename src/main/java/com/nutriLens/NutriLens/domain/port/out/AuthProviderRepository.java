package com.nutriLens.NutriLens.domain.port.out;

import com.nutriLens.NutriLens.domain.model.AuthProvider;
import com.nutriLens.NutriLens.domain.model.AuthProviderType;

import java.util.Optional;

public interface AuthProviderRepository {
    Optional<AuthProvider> findByProviderEmail(String email);
    Optional<AuthProvider> findByProviderUserIdAndType(String providerUserId, AuthProviderType type);
    AuthProvider save(AuthProvider authProvider);
}
