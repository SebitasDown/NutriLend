package com.nutriLens.NutriLens.infrastructure.port.out.persistence.mapper;

import com.nutriLens.NutriLens.domain.model.MealAnalysis;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.document.MealAnalysisDocument;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {MediaInputMapper.class, NutritionProfileMapper.class}
)
public interface MealAnalysisMapper {

    MealAnalysisDocument toDocument(MealAnalysis mealAnalysis);

    MealAnalysis toDomain(MealAnalysisDocument document);
}
