package com.nutriLens.NutriLens.infrastructure.port.out.persistence.adapter;

import com.nutriLens.NutriLens.domain.model.Goal;
import com.nutriLens.NutriLens.domain.model.Recipe;
import com.nutriLens.NutriLens.domain.model.TypeFood;
import com.nutriLens.NutriLens.domain.port.out.RecipeRepository;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.mapper.MapperRecipe;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.repository.MongoRecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RecipeAdapter implements RecipeRepository {

    private final MongoRecipeRepository repository;
    private final MapperRecipe mapperRecipe;

    @Override
    public List<Recipe> findByGoalAndTypeFood(Goal goal, TypeFood typeFood) {
        return repository.findByGoalAndTypeFood(goal, typeFood)
                .stream()
                .map(mapperRecipe::toDomain)
                .toList();
    }
}
