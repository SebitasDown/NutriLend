package com.nutriLens.NutriLens.application.service.analyzeIa;

import com.nutriLens.NutriLens.domain.model.Meal;
import com.nutriLens.NutriLens.domain.model.MealAnalysis;
import com.nutriLens.NutriLens.domain.port.in.analyzeIa.ConfirmMealUseCase;
import com.nutriLens.NutriLens.domain.port.out.MealAnalysisRepository;
import com.nutriLens.NutriLens.domain.port.out.MealRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConfirmMealUseCaseImpl implements ConfirmMealUseCase {

    private final MealAnalysisRepository mealAnalysisRepository;
    private final MealRepository mealRepository;

    public ConfirmMealUseCaseImpl(MealAnalysisRepository mealAnalysisRepository, MealRepository mealRepository) {
        this.mealAnalysisRepository = mealAnalysisRepository;
        this.mealRepository = mealRepository;
    }

    @Override
    @Transactional
    public void confirm(String analysisId, Long userId) {
        MealAnalysis analysis = mealAnalysisRepository.findById(analysisId)
                .orElseThrow(() -> new RuntimeException("Análisis no encontrado: " + analysisId));

        if (!analysis.getUserId().equals(userId)) {
            throw new RuntimeException("No autorizado");
        }

        Meal meal = new Meal(
                null,
                userId,
                analysis.getNutritionProfile().getCalories(),
                analysis.getNutritionProfile().getProtein(),
                analysis.getNutritionProfile().getCarbs(),
                analysis.getNutritionProfile().getFats(),
                analysis.getAnalyzedAt());
        mealRepository.save(meal);
    }
}
