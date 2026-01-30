package com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyNutritionResponseDto {
    private int totalCalories;
    private float totalProtein;
    private float totalCarbs;
    private float totalFats;
    private int calorieGoal;
    private float calorieProgressPercentage;
}
