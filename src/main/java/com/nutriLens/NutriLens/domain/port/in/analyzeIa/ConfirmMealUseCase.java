package com.nutriLens.NutriLens.domain.port.in.analyzeIa;

public interface ConfirmMealUseCase {
    void confirm(String analysisId, Long userId);
}
