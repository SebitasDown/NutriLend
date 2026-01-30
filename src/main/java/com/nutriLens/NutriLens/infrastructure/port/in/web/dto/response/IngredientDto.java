package com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Ingrediente de una receta")
public class IngredientDto {
    @Schema(description = "Nombre del ingrediente", example = "Pechuga de pollo")
    private String name;

    @Schema(description = "Cantidad necesaria", example = "200g")
    private String quantity;
}
