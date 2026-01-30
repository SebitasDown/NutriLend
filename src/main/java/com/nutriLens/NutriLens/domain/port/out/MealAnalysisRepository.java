package com.nutriLens.NutriLens.domain.port.out;

import com.nutriLens.NutriLens.domain.model.MealAnalysis;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface MealAnalysisRepository {

    MealAnalysis save(MealAnalysis analysis);

    Optional<MealAnalysis> findById(String id);

    List<MealAnalysis> findByUserId(Long userId);

    List<MealAnalysis> findByUserAndDateRange(Long userId, Instant start, Instant end);
}
