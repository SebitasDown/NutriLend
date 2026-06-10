package com.nutriLens.NutriLens.infrastructure.port.in.web.dto.request;

import com.nutriLens.NutriLens.domain.model.MealType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Solicitud de análisis nutricional por descripción textual")
public class AnalyzeTextRequest {

    @Schema(description = "Descripción textual de la comida (ingredientes, porciones, etc.)", example = "Un plato de pasta con salsa boloñesa y queso parmesano")
    private String description;

    @Schema(description = "Tipo de comida: BREAKFAST, LUNCH, DINNER, SNACK")
    private MealType mealType;
}
