package com.nutriLens.NutriLens.application.service.recipe;

import com.nutriLens.NutriLens.domain.model.Recipe;
import com.nutriLens.NutriLens.domain.model.TypeFood;
import com.nutriLens.NutriLens.domain.port.in.recipe.GetRecipesForUserUseCase;
import com.nutriLens.NutriLens.domain.port.out.RecipeRepository;

import java.util.List;

public class GetRecipesByTypeFoodUseCase implements GetRecipesForUserUseCase {

    private final RecipeRepository recipeRepository;

    public GetRecipesByTypeFoodUseCase(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Recipe> execute(Long userId, TypeFood typeFood) {
        return recipeRepository.findByGoalAndTypeFood(userId, typeFood);
    }
}
