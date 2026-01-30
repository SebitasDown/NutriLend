package com.nutriLens.NutriLens.infrastructure.port.out.persistence.repository;

import com.nutriLens.NutriLens.infrastructure.port.out.persistence.document.MealDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface MongoMealRepository extends MongoRepository<MealDocument, String> {
    List<MealDocument> findByUserIdAndEatenAtBetween(Long userId, Instant start, Instant end);
}
