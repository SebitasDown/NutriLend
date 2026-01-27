package com.nutriLens.NutriLens.application.service.recipe;

import com.nutriLens.NutriLens.application.exception.NotFound;
import com.nutriLens.NutriLens.domain.model.*;
import com.nutriLens.NutriLens.domain.port.in.recipe.GetRecipesForUserUseCase;
import com.nutriLens.NutriLens.domain.port.out.RecipeRepository;
import com.nutriLens.NutriLens.domain.port.out.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetRecipesForUserUseCaseImpl implements GetRecipesForUserUseCase {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    public GetRecipesForUserUseCaseImpl(RecipeRepository recipeRepository, UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Recipe> execute(Long userId, TypeFood typeFood) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFound("Usuario no encontrado"));

        Goal goal = user.getGoal();
        Preference preference = user.getPreference();

        List<Recipe> recipes = recipeRepository.findByGoalAndTypeFood(goal, typeFood);

        if (preference == Preference.VEGETARIANO){
            return recipes.stream()
                    .filter(Recipe::isVegetarian)
                    .toList();
        }

        return recipes;
    }
}
