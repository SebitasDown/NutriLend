package com.nutriLens.NutriLens.infrastructure.port.out.persistence.document;

import com.nutriLens.NutriLens.domain.model.MealType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document(collection = "meal_analysis")
@AllArgsConstructor
@NoArgsConstructor
public class MealAnalysisDocument {

    @Id
    private String id;

    private Long userId;
    private MediaInputDocument media;
    private NutritionProfileDocument nutritionProfile;
    private MealType mealType;
    private Instant analyzedAt;
}
