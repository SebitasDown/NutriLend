package com.nutriLens.NutriLens.domain.port.in.analyzeIa;

import com.nutriLens.NutriLens.domain.model.MealAnalysis;

import java.util.List;

public interface GetMealHistoryUseCase {
    List<MealAnalysis> execute(Long userId);
}
