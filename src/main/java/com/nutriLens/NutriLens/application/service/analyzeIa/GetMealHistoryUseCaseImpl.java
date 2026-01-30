package com.nutriLens.NutriLens.application.service.analyzeIa;

import com.nutriLens.NutriLens.domain.model.MealAnalysis;
import com.nutriLens.NutriLens.domain.port.in.analyzeIa.GetMealHistoryUseCase;
import com.nutriLens.NutriLens.domain.port.out.MealAnalysisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetMealHistoryUseCaseImpl implements GetMealHistoryUseCase {

    private final MealAnalysisRepository mealAnalysisRepository;

    @Override
    public List<MealAnalysis> execute(Long userId) {
        return mealAnalysisRepository.findByUserId(userId);
    }
}
