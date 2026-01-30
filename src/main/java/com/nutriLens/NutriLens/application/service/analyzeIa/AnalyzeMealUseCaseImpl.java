package com.nutriLens.NutriLens.application.service.analyzeIa;

import com.nutriLens.NutriLens.domain.model.Meal;
import com.nutriLens.NutriLens.domain.model.MealAnalysis;
import com.nutriLens.NutriLens.domain.model.MediaInput;
import com.nutriLens.NutriLens.domain.model.MediaType;
import com.nutriLens.NutriLens.domain.model.MealType;
import com.nutriLens.NutriLens.domain.model.NutritionProfile;
import com.nutriLens.NutriLens.domain.port.in.analyzeIa.AnalyzeMealUseCase;
import com.nutriLens.NutriLens.domain.port.out.MealAiPort;
import com.nutriLens.NutriLens.domain.port.out.MealAnalysisRepository;
import com.nutriLens.NutriLens.domain.port.out.MealRepository;
import com.nutriLens.NutriLens.domain.port.out.MediaStoragePort;

import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AnalyzeMealUseCaseImpl implements AnalyzeMealUseCase {

    private final MediaStoragePort mediaStoragePort;
    private final MealAiPort mealAiPort;
    private final MealAnalysisRepository mealAnalysisRepository;
    private final MealRepository mealRepository;

    public AnalyzeMealUseCaseImpl(MediaStoragePort mediaStoragePort, MealAiPort mealAiPort,
            MealAnalysisRepository mealAnalysisRepository, MealRepository mealRepository) {
        this.mediaStoragePort = mediaStoragePort;
        this.mealAiPort = mealAiPort;
        this.mealAnalysisRepository = mealAnalysisRepository;
        this.mealRepository = mealRepository;
    }

    @Override
    public MealAnalysis analyze(Long userId, byte[] fileBytes, MediaType type, MealType mealType) {
        String cloudinaryUrl = mediaStoragePort.upload(fileBytes, type);

        NutritionProfile nutritionProfile = mealAiPort.analyze(fileBytes, type);

        MediaInput mediaInput = new MediaInput(
                type,
                cloudinaryUrl);

        Instant now = Instant.now();
        MealAnalysis analysis = new MealAnalysis(
                null,
                userId,
                mediaInput,
                nutritionProfile,
                mealType,
                now);

        MealAnalysis savedAnalysis = mealAnalysisRepository.save(analysis);

        // También guardamos la comida en el diario nutricional
        Meal meal = new Meal(
                null,
                userId,
                nutritionProfile.getCalories(),
                nutritionProfile.getProtein(),
                nutritionProfile.getCarbs(),
                nutritionProfile.getFats(),
                now);
        mealRepository.save(meal);

        return savedAnalysis;
    }
}
