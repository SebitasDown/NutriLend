package com.nutriLens.NutriLens.infrastructure.port.out.persistence.repository;

import com.nutriLens.NutriLens.domain.model.VerificationToken;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.entity.VerificationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaVerificationTokenRepository extends JpaRepository<VerificationTokenEntity, Long> {
    Optional<VerificationTokenEntity> findByEmailAndCodeAndTypeAndUsedFalse(
            String email, String code, VerificationToken.TokenType type);
}
