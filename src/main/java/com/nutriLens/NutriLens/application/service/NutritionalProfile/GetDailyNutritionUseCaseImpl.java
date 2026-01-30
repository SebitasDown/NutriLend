package com.nutriLens.NutriLens.application.service.NutritionalProfile;

import com.nutriLens.NutriLens.domain.model.DailyNutrition;
import com.nutriLens.NutriLens.domain.model.Meal;
import com.nutriLens.NutriLens.domain.model.MealAnalysis;
import com.nutriLens.NutriLens.domain.model.User;
import com.nutriLens.NutriLens.domain.port.in.NutritionProfile.GetDailyNutritionUseCase;
import com.nutriLens.NutriLens.domain.port.out.MealAnalysisRepository;
import com.nutriLens.NutriLens.domain.port.out.MealRepository;
import com.nutriLens.NutriLens.domain.port.out.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class GetDailyNutritionUseCaseImpl implements GetDailyNutritionUseCase {

    private final MealRepository mealRepository;
    private final MealAnalysisRepository mealAnalysisRepository;
    private final UserRepository userRepository;

    public GetDailyNutritionUseCaseImpl(MealRepository mealRepository,
            MealAnalysisRepository mealAnalysisRepository,
            UserRepository userRepository) {
        this.mealRepository = mealRepository;
        this.mealAnalysisRepository = mealAnalysisRepository;
        this.userRepository = userRepository;
    }

    @Override
    public DailyNutrition getDailyNutrition(Long userId, LocalDate date, ZoneOffset offset) {
        // Obtenemos comidas del diario
        List<Meal> meals = mealRepository.findByUserAndDate(userId, date, offset);

        // También obtenemos análisis del historial (por si alguno no se guardó en el
        // diario)
        Instant start = date.atStartOfDay().toInstant(offset);
        Instant end = date.atTime(LocalTime.MAX).toInstant(offset);
        List<MealAnalysis> analyses = mealAnalysisRepository.findByUserAndDateRange(userId, start, end);

        // Sumamos calorías
        int totalCalories = meals.stream().mapToInt(Meal::getCalories).sum();
        totalCalories += analyses.stream()
                .filter(a -> meals.stream().noneMatch(m -> m.getEatenAt().equals(a.getAnalyzedAt()))) // Evitar
                                                                                                      // duplicados si
                                                                                                      // ya están en el
                                                                                                      // diario
                .mapToInt(a -> a.getNutritionProfile().getCalories())
                .sum();

        // Sumamos proteínas
        float totalProtein = (float) meals.stream().mapToDouble(Meal::getProtein).sum();
        totalProtein += (float) analyses.stream()
                .filter(a -> meals.stream().noneMatch(m -> m.getEatenAt().equals(a.getAnalyzedAt())))
                .mapToDouble(a -> a.getNutritionProfile().getProtein())
                .sum();

        // Sumamos carbohidratos
        float totalCarbs = (float) meals.stream().mapToDouble(Meal::getCarbs).sum();
        totalCarbs += (float) analyses.stream()
                .filter(a -> meals.stream().noneMatch(m -> m.getEatenAt().equals(a.getAnalyzedAt())))
                .mapToDouble(a -> a.getNutritionProfile().getCarbs())
                .sum();

        // Sumamos grasas
        float totalFats = (float) meals.stream().mapToDouble(Meal::getFats).sum();
        totalFats += (float) analyses.stream()
                .filter(a -> meals.stream().noneMatch(m -> m.getEatenAt().equals(a.getAnalyzedAt())))
                .mapToDouble(a -> a.getNutritionProfile().getFats())
                .sum();

        int calorieGoal = calculateCalorieGoal(userId);

        return new DailyNutrition(totalCalories, totalProtein, totalCarbs, totalFats, calorieGoal);
    }

    private int calculateCalorieGoal(Long userId) {
        return userRepository.findById(userId)
                .map(this::calculateTDEE)
                .orElse(2000);
    }

    private int calculateTDEE(User user) {
        if (user.getWeight() == null || user.getHeight() == null || user.getAge() == null) {
            return 2000;
        }

        double bmr = 10 * user.getWeight() + 6.25 * user.getHeight() - 5 * user.getAge() + 5;

        double multiplier = switch (user.getActivityLevel()) {
            case LOW -> 1.2;
            case MEDIUM -> 1.55;
            case HIGH -> 1.725;
            default -> 1.2;
        };

        double tdee = bmr * multiplier;

        return switch (user.getGoal()) {
            case LOSE_WEIGHT -> (int) (tdee - 500);
            case GAIN_MUSCLE -> (int) (tdee + 500);
            case MAINTAIN_WEIGHT -> (int) tdee;
            default -> (int) tdee;
        };
    }
}
