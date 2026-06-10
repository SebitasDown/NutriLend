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

        public AnalyzeMealUseCaseImpl(MediaStoragePort mediaStoragePort, MealAiPort mealAiPort,
                        MealAnalysisRepository mealAnalysisRepository) {
                this.mediaStoragePort = mediaStoragePort;
                this.mealAiPort = mealAiPort;
                this.mealAnalysisRepository = mealAnalysisRepository;
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
                                now,
                                false);

                return mealAnalysisRepository.save(analysis);
        }

        @Override
        public MealAnalysis analyzeText(Long userId, String description, MealType mealType) {
                NutritionProfile nutritionProfile = mealAiPort.analyzeText(description);

                MediaInput mediaInput = new MediaInput(
                                MediaType.TEXT,
                                null);

                Instant now = Instant.now();
                MealAnalysis analysis = new MealAnalysis(
                                null,
                                userId,
                                mediaInput,
                                nutritionProfile,
                                mealType,
                                now,
                                false);

                return mealAnalysisRepository.save(analysis);
        }
}
