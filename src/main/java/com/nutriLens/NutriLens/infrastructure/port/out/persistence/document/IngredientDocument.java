package com.nutriLens.NutriLens.infrastructure.port.out.persistence.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDocument {
    private String name;
    private String quantity;
}
