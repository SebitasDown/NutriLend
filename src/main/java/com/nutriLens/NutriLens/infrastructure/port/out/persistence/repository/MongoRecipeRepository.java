package com.nutriLens.NutriLens.infrastructure.port.out.persistence.repository;

import com.nutriLens.NutriLens.domain.model.Goal;
import com.nutriLens.NutriLens.domain.model.TypeFood;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.document.RecipeDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongoRecipeRepository extends MongoRepository<RecipeDocument, String> {
    List<RecipeDocument> findByGoalAndTypeFood(Goal goal, TypeFood typeFood);
}
