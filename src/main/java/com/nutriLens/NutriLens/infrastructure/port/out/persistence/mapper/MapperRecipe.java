package com.nutriLens.NutriLens.infrastructure.port.out.persistence.mapper;

import com.nutriLens.NutriLens.domain.model.Ingredient;
import com.nutriLens.NutriLens.domain.model.Recipe;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.document.IngredientDocument;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.document.RecipeDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperRecipe {
    Recipe toDomain(RecipeDocument document);

    Ingredient toDomain(IngredientDocument document);
}
