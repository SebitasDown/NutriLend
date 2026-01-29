package com.nutriLens.NutriLens.domain.model;

import java.time.Instant;

public class MealAnalysis {
    private String id;
    private Long userId;
    private MediaInput media;
    private NutritionProfile nutritionProfile;
    private Instant analyzedAt;

    public MealAnalysis(String id, Long userId, MediaInput media, NutritionProfile nutritionProfile, Instant analyzedAt) {
        this.id = id;
        this.userId = userId;
        this.media = media;
        this.nutritionProfile = nutritionProfile;
        this.analyzedAt = analyzedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public MediaInput getMedia() {
        return media;
    }

    public NutritionProfile getNutritionProfile() {
        return nutritionProfile;
    }

    public Instant getAnalyzedAt() {
        return analyzedAt;
    }
}
