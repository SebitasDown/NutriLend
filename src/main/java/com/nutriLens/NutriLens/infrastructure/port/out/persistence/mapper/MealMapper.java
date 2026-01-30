package com.nutriLens.NutriLens.infrastructure.port.out.persistence.mapper;

import com.nutriLens.NutriLens.domain.model.Meal;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.document.MealDocument;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MealMapper {

    MealDocument toDocument(Meal meal);

    Meal toDomain(MealDocument document);
}
