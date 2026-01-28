package com.nutriLens.NutriLens.infrastructure.port.in.web.mapper;

import com.nutriLens.NutriLens.domain.model.Ingredient;
import com.nutriLens.NutriLens.domain.model.Recipe;
import com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response.IngredientDto;
import com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response.RecipeResponseDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface RecipeDtoMapper {

    RecipeResponseDto toDto(Recipe recipe);

    IngredientDto toDto(Ingredient ingredient);
}
