package com.nutriLens.NutriLens.domain.port.out;

import com.nutriLens.NutriLens.domain.model.VerificationToken;

import java.util.Optional;

public interface VerificationTokenRepository {
    void save(VerificationToken token);
    Optional<VerificationToken> findByEmailAndCodeAndType(String email, String code, VerificationToken.TokenType type);
    void markAsUsed(Long id);
}
