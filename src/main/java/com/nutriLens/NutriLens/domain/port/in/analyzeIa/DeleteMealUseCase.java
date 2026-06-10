package com.nutriLens.NutriLens.domain.port.in.analyzeIa;

public interface DeleteMealUseCase {
    void delete(String analysisId, Long userId);
}
