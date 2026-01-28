package com.nutriLens.NutriLens.infrastructure.port.out.persistence.adapter;

import com.nutriLens.NutriLens.domain.model.AuthProvider;
import com.nutriLens.NutriLens.domain.model.AuthProviderType;
import com.nutriLens.NutriLens.domain.port.out.AuthProviderRepository;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.entity.AuthProviderEntity;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.mapper.AuthProviderMapper;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.repository.JpaAuthProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthProviderAdapter implements AuthProviderRepository {

    private final JpaAuthProviderRepository jpaAuthProviderRepository;
    private final AuthProviderMapper authProviderMapper;

    @Override
    public Optional<AuthProvider> findByProviderEmail(String email) {
        return jpaAuthProviderRepository.findByProviderEmail(email)
                .map(authProviderMapper::toDomain);
    }

    @Override
    public Optional<AuthProvider> findByProviderUserIdAndType(String providerUserId, AuthProviderType type) {
        return jpaAuthProviderRepository.findByProviderUserIdAndAuthProviderType(providerUserId, type)
                .map(authProviderMapper::toDomain);
    }

    @Override
    public AuthProvider save(AuthProvider authProvider) {
        AuthProviderEntity entity = authProviderMapper.toEntity(authProvider);
        AuthProviderEntity savedEntity = jpaAuthProviderRepository.save(entity);
        return authProviderMapper.toDomain(savedEntity);
    }
}
