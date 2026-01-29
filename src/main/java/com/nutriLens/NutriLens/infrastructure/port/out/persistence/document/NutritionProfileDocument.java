package com.nutriLens.NutriLens.infrastructure.port.out.persistence.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NutritionProfileDocument {

    private int calories;
    private float protein;
    private float carbs;
    private float fats;

}
