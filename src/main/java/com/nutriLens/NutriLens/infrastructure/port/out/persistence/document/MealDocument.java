package com.nutriLens.NutriLens.infrastructure.port.out.persistence.document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "meals")
public class MealDocument {

    @Id
    private String id;

    private Long userId;
    private int calories;
    private float protein;
    private float carbs;
    private float fats;
    private Instant eatenAt;
}
