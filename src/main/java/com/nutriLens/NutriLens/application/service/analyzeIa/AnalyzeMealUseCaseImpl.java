package com.nutriLens.NutriLens.application.service.analyzeIa;

import com.nutriLens.NutriLens.domain.model.MealAnalysis;
import com.nutriLens.NutriLens.domain.model.MediaInput;
import com.nutriLens.NutriLens.domain.model.MediaType;
import com.nutriLens.NutriLens.domain.model.NutritionProfile;
import com.nutriLens.NutriLens.domain.port.in.analyzeIa.AnalyzeMealUseCase;
import com.nutriLens.NutriLens.domain.port.out.MealAiPort;
import com.nutriLens.NutriLens.domain.port.out.MealAnalysisRepository;
import com.nutriLens.NutriLens.domain.port.out.MediaStoragePort;

import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AnalyzeMealUseCaseImpl implements AnalyzeMealUseCase {

    private final MediaStoragePort mediaStoragePort;
    private final MealAiPort mealAiPort;
    private final MealAnalysisRepository mealAnalysisRepository;

    public AnalyzeMealUseCaseImpl(MediaStoragePort mediaStoragePort, MealAiPort mealAiPort, MealAnalysisRepository mealAnalysisRepository) {
        this.mediaStoragePort = mediaStoragePort;
        this.mealAiPort = mealAiPort;
        this.mealAnalysisRepository = mealAnalysisRepository;
    }


    @Override
    public MealAnalysis analyze(Long userId, byte[] fileBytes, MediaType type) {
        String cloudinaryUrl = mediaStoragePort.upload(fileBytes, type);

        NutritionProfile nutritionProfile = mealAiPort.analyze(fileBytes, type);

        MediaInput mediaInput = new MediaInput(
                type,
                cloudinaryUrl
        );

        MealAnalysis analysis = new MealAnalysis(
                null,
                userId,
                mediaInput,
                nutritionProfile,
                Instant.now()
        );

        return mealAnalysisRepository.save(analysis);
    }
}
