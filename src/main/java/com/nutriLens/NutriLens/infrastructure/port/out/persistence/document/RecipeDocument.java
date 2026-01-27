package com.nutriLens.NutriLens.infrastructure.port.out.persistence.document;

import com.nutriLens.NutriLens.domain.model.Goal;
import com.nutriLens.NutriLens.domain.model.TypeFood;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collation = "recipes")
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDocument {

    @Id
    private String id;

    private String name;
    private Goal goal;
    private TypeFood typeFood;

    private boolean vegetarian;

    private Integer time;
    private String description;
    private Integer portion;
    private Integer calories;
    private String image;

    private List<IngredientDocument> ingredients;
    private List<String> steps;
}
