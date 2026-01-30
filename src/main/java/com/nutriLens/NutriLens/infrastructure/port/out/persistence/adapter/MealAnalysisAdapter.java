package com.nutriLens.NutriLens.infrastructure.port.out.persistence.adapter;

import com.nutriLens.NutriLens.domain.model.MealAnalysis;
import com.nutriLens.NutriLens.domain.port.out.MealAnalysisRepository;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.document.MealAnalysisDocument;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.mapper.MealAnalysisMapper;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.repository.MongoMealAnalysisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MealAnalysisAdapter implements MealAnalysisRepository {

    private final MongoMealAnalysisRepository repository;
    private final MealAnalysisMapper mapper;

    @Override
    public MealAnalysis save(MealAnalysis analysis) {
        MealAnalysisDocument doc = mapper.toDocument(analysis);
        MealAnalysisDocument saved = repository.save(doc);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<MealAnalysis> findById(String id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<MealAnalysis> findByUserId(Long userId) {
        return repository.findByUserIdOrderByAnalyzedAtDesc(userId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<MealAnalysis> findByUserAndDateRange(Long userId, Instant start, Instant end) {
        return repository.findByUserIdAndAnalyzedAtBetween(userId, start, end)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
