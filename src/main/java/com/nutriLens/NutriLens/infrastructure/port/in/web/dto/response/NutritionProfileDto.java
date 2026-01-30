package com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Perfil nutricional de una comida analizada")
public class NutritionProfileDto {
    @Schema(description = "Calorías totales", example = "450")
    private int calories;

    @Schema(description = "Proteínas en gramos", example = "25.5")
    private float protein;

    @Schema(description = "Carbohidratos en gramos", example = "45.0")
    private float carbs;

    @Schema(description = "Grasas en gramos", example = "15.3")
    private float fats;
}
