package com.nutriLens.NutriLens.domain.port.in.recipe;

import com.nutriLens.NutriLens.domain.model.Recipe;
import com.nutriLens.NutriLens.domain.model.TypeFood;

import java.util.List;

public interface GetRecipesForUserUseCase {
    List<Recipe> execute(Long userId, TypeFood typeFood);
}
