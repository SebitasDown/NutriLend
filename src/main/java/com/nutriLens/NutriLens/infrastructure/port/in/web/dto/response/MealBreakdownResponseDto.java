package com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Desglose nutricional por tipo de comida")
public class MealBreakdownResponseDto {
    @Schema(description = "Fecha consultada")
    private String date;

    private TypeNutrition breakfast;
    private TypeNutrition lunch;
    private TypeNutrition dinner;
    private TypeNutrition snack;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(description = "Nutrición agrupada por tipo de comida")
    public static class TypeNutrition {
        private int calories;
        private float protein;
        private float carbs;
        private float fats;
        private int count;
    }
}
