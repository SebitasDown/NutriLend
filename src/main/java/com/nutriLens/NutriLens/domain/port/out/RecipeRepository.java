package com.nutriLens.NutriLens.domain.port.out;

import com.nutriLens.NutriLens.domain.model.Goal;
import com.nutriLens.NutriLens.domain.model.Recipe;
import com.nutriLens.NutriLens.domain.model.TypeFood;

import java.util.List;


public interface RecipeRepository {
    List<Recipe> findByGoalAndTypeFood(Goal goal, TypeFood typeFood);
}
