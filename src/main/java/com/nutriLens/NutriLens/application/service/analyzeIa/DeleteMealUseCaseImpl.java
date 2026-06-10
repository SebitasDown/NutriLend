package com.nutriLens.NutriLens.application.service.analyzeIa;

import com.nutriLens.NutriLens.domain.model.MealAnalysis;
import com.nutriLens.NutriLens.domain.port.in.analyzeIa.DeleteMealUseCase;
import com.nutriLens.NutriLens.domain.port.out.MealAnalysisRepository;
import com.nutriLens.NutriLens.domain.port.out.MealRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteMealUseCaseImpl implements DeleteMealUseCase {

    private final MealAnalysisRepository mealAnalysisRepository;
    private final MealRepository mealRepository;

    public DeleteMealUseCaseImpl(MealAnalysisRepository mealAnalysisRepository, MealRepository mealRepository) {
        this.mealAnalysisRepository = mealAnalysisRepository;
        this.mealRepository = mealRepository;
    }

    @Override
    @Transactional
    public void delete(String analysisId, Long userId) {
        MealAnalysis analysis = mealAnalysisRepository.findById(analysisId)
                .orElseThrow(() -> new RuntimeException("Análisis no encontrado: " + analysisId));

        if (!analysis.getUserId().equals(userId)) {
            throw new RuntimeException("No autorizado");
        }

        mealAnalysisRepository.softDelete(analysisId);
        mealRepository.softDeleteByUserAndTimestamp(userId, analysis.getAnalyzedAt());
    }
}
