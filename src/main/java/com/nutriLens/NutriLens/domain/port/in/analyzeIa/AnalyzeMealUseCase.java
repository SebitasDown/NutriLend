package com.nutriLens.NutriLens.domain.port.in.analyzeIa;

import com.nutriLens.NutriLens.domain.model.MealAnalysis;
import com.nutriLens.NutriLens.domain.model.MediaType;
import com.nutriLens.NutriLens.domain.model.MealType;

public interface AnalyzeMealUseCase {

    MealAnalysis analyze(Long userId, byte[] fileBytes, MediaType type, MealType mealType);
}
