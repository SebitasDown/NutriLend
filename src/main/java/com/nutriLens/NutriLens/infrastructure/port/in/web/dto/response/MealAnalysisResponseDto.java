package com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response;

import com.nutriLens.NutriLens.domain.model.MediaType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealAnalysisResponseDto {
    private String id;
    private String mediaUrl;
    private MediaType mediaType;
    private NutritionProfileDto nutritionProfile;
    private Instant analyzedAt;
}
