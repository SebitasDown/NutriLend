package com.nutriLens.NutriLens.infrastructure.port.out.persistence.mapper;

import com.nutriLens.NutriLens.domain.model.NutritionProfile;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.document.NutritionProfileDocument;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NutritionProfileMapper {

    NutritionProfileDocument toDocument(NutritionProfile nutritionProfile);

    NutritionProfile toDomain(NutritionProfileDocument document);
}
