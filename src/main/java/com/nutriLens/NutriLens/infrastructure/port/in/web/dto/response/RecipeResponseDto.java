package com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Información detallada de una receta")
public class RecipeResponseDto {
    @Schema(description = "ID único de la receta", example = "recipe-123")
    private String id;

    @Schema(description = "Nombre de la receta", example = "Ensalada César")
    private String name;

    @Schema(description = "Tipo de comida", example = "LUNCH")
    private String typeFood;

    @Schema(description = "Tiempo de preparación en minutos", example = "30")
    private Integer time;

    @Schema(description = "Descripción de la receta", example = "Una deliciosa ensalada con pollo a la parrilla...")
    private String description;

    @Schema(description = "Número de porciones", example = "2")
    private Integer portion;

    @Schema(description = "Calorías por porción", example = "350")
    private Integer calories;

    @Schema(description = "URL de la imagen de la receta", example = "https://cloudinary.com/recipe.jpg")
    private String image;

    @Schema(description = "Lista de ingredientes necesarios")
    private List<IngredientDto> ingredients;

    @Schema(description = "Pasos de preparación", example = "[\"Lavar la lechuga\", \"Cortar el pollo\", \"Mezclar todo\"]")
    private List<String> steps;
}
