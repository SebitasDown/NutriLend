package com.nutriLens.NutriLens.infrastructure.port.out.persistence.repository;

import com.nutriLens.NutriLens.infrastructure.port.out.persistence.entity.AuthProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nutriLens.NutriLens.domain.model.AuthProviderType;
import java.util.Optional;

public interface JpaAuthProviderRepository extends JpaRepository<AuthProviderEntity, Long> {
    Optional<AuthProviderEntity> findByProviderUserIdAndAuthProviderType(String providerUserId,
            AuthProviderType authProviderType);

    Optional<AuthProviderEntity> findByProviderEmail(String providerEmail);
}
