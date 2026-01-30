package com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Resumen nutricional diario del usuario")
public class DailyNutritionResponseDto {
    @Schema(description = "Total de calorías consumidas en el día", example = "1850")
    private int totalCalories;

    @Schema(description = "Total de proteínas consumidas en gramos", example = "65.5")
    private float totalProtein;

    @Schema(description = "Total de carbohidratos consumidos en gramos", example = "220.3")
    private float totalCarbs;

    @Schema(description = "Total de grasas consumidas en gramos", example = "55.2")
    private float totalFats;

    @Schema(description = "Meta de calorías diarias del usuario", example = "2000")
    private int calorieGoal;

    @Schema(description = "Porcentaje de progreso hacia la meta calórica", example = "92.5")
    private float calorieProgressPercentage;
}
