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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AnalyzeMealUseCaseImpl implements AnalyzeMealUseCase {

        private static final Logger log = LoggerFactory.getLogger(AnalyzeMealUseCaseImpl.class);

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
                log.info("=== AnalyzeMealUseCaseImpl.analyze() - userId={}, type={}, mealType={}, bytes={} ===",
                        userId, type, mealType, fileBytes.length);

                String cloudinaryUrl = mediaStoragePort.upload(fileBytes, type);
                log.info("Archivo subido a Cloudinary: {}", cloudinaryUrl);

                log.info("Enviando a IA para analisis {}...", type);
                NutritionProfile nutritionProfile = mealAiPort.analyze(fileBytes, type);
                log.info("IA respondio: {} kcal, {}g protein, {}g carbs, {}g fats",
                        nutritionProfile.getCalories(), nutritionProfile.getProtein(),
                        nutritionProfile.getCarbs(), nutritionProfile.getFats());

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

                MealAnalysis saved = mealAnalysisRepository.save(analysis);
                log.info("Analisis guardado con id: {}", saved.getId());
                return saved;
        }

        @Override
        public MealAnalysis analyzeText(Long userId, String description, MealType mealType) {
                log.info("=== AnalyzeMealUseCaseImpl.analyzeText() - userId={}, mealType={} ===",
                        userId, mealType);
                log.info("Descripcion: {}", description);

                NutritionProfile nutritionProfile = mealAiPort.analyzeText(description);
                log.info("IA respondio (texto): {} kcal, {}g protein, {}g carbs, {}g fats",
                        nutritionProfile.getCalories(), nutritionProfile.getProtein(),
                        nutritionProfile.getCarbs(), nutritionProfile.getFats());

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

                MealAnalysis saved = mealAnalysisRepository.save(analysis);
                log.info("Analisis de texto guardado con id: {}", saved.getId());
                return saved;
        }
}
