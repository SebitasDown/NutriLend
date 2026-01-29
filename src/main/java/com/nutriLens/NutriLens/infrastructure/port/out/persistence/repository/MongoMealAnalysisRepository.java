package com.nutriLens.NutriLens.infrastructure.port.out.persistence.repository;

import com.nutriLens.NutriLens.infrastructure.port.out.persistence.document.MealAnalysisDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongoMealAnalysisRepository extends MongoRepository<MealAnalysisDocument, String> {
    List<MealAnalysisDocument> findByUserIdOrderByAnalyzedAtDesc(Long userId);
}
