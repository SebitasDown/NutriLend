package com.nutriLens.NutriLens.infrastructure.port.in.web.mapper;

import com.nutriLens.NutriLens.domain.model.MealAnalysis;
import com.nutriLens.NutriLens.domain.model.NutritionProfile;
import com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response.MealAnalysisResponseDto;
import com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response.NutritionProfileDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MealAnalysisDtoMapper {

    @Mapping(source = "media.cloudinaryUrl", target = "mediaUrl")
    @Mapping(source = "media.type", target = "mediaType")
    MealAnalysisResponseDto toDto(MealAnalysis mealAnalysis);

    NutritionProfileDto toDto(NutritionProfile nutritionProfile);
}
