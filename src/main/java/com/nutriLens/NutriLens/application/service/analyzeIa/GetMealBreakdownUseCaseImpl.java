package com.nutriLens.NutriLens.application.service.analyzeIa;

import com.nutriLens.NutriLens.domain.model.MealAnalysis;
import com.nutriLens.NutriLens.domain.model.MealType;
import com.nutriLens.NutriLens.domain.port.in.analyzeIa.GetMealBreakdownUseCase;
import com.nutriLens.NutriLens.domain.port.out.MealAnalysisRepository;
import com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response.MealBreakdownResponseDto;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class GetMealBreakdownUseCaseImpl implements GetMealBreakdownUseCase {

    private final MealAnalysisRepository mealAnalysisRepository;

    public GetMealBreakdownUseCaseImpl(MealAnalysisRepository mealAnalysisRepository) {
        this.mealAnalysisRepository = mealAnalysisRepository;
    }

    @Override
    public MealBreakdownResponseDto execute(Long userId, LocalDate date, ZoneOffset offset) {
        Instant start = date.atStartOfDay().toInstant(offset);
        Instant end = date.atTime(LocalTime.MAX).toInstant(offset);

        List<MealAnalysis> analyses = mealAnalysisRepository.findByUserAndDateRange(userId, start, end);

        MealBreakdownResponseDto.TypeNutrition breakfast = aggregate(analyses, MealType.BREAKFAST);
        MealBreakdownResponseDto.TypeNutrition lunch = aggregate(analyses, MealType.LUNCH);
        MealBreakdownResponseDto.TypeNutrition dinner = aggregate(analyses, MealType.DINNER);
        MealBreakdownResponseDto.TypeNutrition snack = aggregate(analyses, MealType.SNACK);

        return new MealBreakdownResponseDto(date.toString(), breakfast, lunch, dinner, snack);
    }

    private MealBreakdownResponseDto.TypeNutrition aggregate(List<MealAnalysis> analyses, MealType type) {
        int calories = 0;
        float protein = 0;
        float carbs = 0;
        float fats = 0;
        int count = 0;

        for (MealAnalysis a : analyses) {
            if (a.getMealType() == type) {
                calories += a.getNutritionProfile().getCalories();
                protein += a.getNutritionProfile().getProtein();
                carbs += a.getNutritionProfile().getCarbs();
                fats += a.getNutritionProfile().getFats();
                count++;
            }
        }

        return new MealBreakdownResponseDto.TypeNutrition(calories, protein, carbs, fats, count);
    }
}
