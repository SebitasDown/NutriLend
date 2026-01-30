package com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response;

import com.nutriLens.NutriLens.domain.model.MediaType;
import com.nutriLens.NutriLens.domain.model.MealType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Resultado del análisis nutricional de una comida")
public class MealAnalysisResponseDto {
    @Schema(description = "ID único del análisis", example = "meal-abc123")
    private String id;

    @Schema(description = "URL del archivo multimedia analizado", example = "https://cloudinary.com/meal.jpg")
    private String mediaUrl;

    @Schema(description = "Tipo de multimedia: IMAGE o AUDIO")
    private MediaType mediaType;

    @Schema(description = "Tipo de comida: BREAKFAST, LUNCH, DINNER, SNACK")
    private MealType mealType;

    @Schema(description = "Perfil nutricional calculado por la IA")
    private NutritionProfileDto nutritionProfile;

    @Schema(description = "Fecha y hora del análisis", example = "2025-01-30T15:30:00Z")
    private Instant analyzedAt;
}
