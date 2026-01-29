package com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NutritionProfileDto {
    private int calories;
    private float protein;
    private float carbs;
    private float fats;
}
