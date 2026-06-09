package com.nutriLens.NutriLens.infrastructure.port.out.persistence.adapter;

import com.nutriLens.NutriLens.domain.model.VerificationToken;
import com.nutriLens.NutriLens.domain.port.out.VerificationTokenRepository;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.entity.VerificationTokenEntity;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.repository.JpaVerificationTokenRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class VerificationTokenRepositoryAdapter implements VerificationTokenRepository {

    private final JpaVerificationTokenRepository jpaRepo;

    public VerificationTokenRepositoryAdapter(JpaVerificationTokenRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public void save(VerificationToken token) {
        jpaRepo.save(new VerificationTokenEntity(token));
    }

    @Override
    public Optional<VerificationToken> findByEmailAndCodeAndType(String email, String code, VerificationToken.TokenType type) {
        return jpaRepo.findByEmailAndCodeAndTypeAndUsedFalse(email, code, type)
                .map(VerificationTokenEntity::toDomain);
    }

    @Override
    public void markAsUsed(Long id) {
        jpaRepo.findById(id).ifPresent(entity -> {
            entity.setUsed(true);
            jpaRepo.save(entity);
        });
    }
}
