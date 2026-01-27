package com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeResponseDto {
    private String id;
    private String name;
    private String typeFood;
    private Integer time;
    private String description;
    private Integer portion;
    private Integer calories;
    private String image;

    private List<IngredientDto> ingredients;
    private List<String> steps;
}
