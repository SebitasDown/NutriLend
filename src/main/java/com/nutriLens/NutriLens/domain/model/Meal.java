package com.nutriLens.NutriLens.domain.model;

import java.time.Instant;

public class Meal {
    private String id;
    private Long userId;
    private int calories;
    private float protein;
    private float carbs;
    private float fats;
    private Instant eatenAt;

    public Meal(String id, Long userId, int calories, float protein, float carbs, float fats, Instant eatenAt) {
        this.id = id;
        this.userId = userId;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
        this.eatenAt = eatenAt;
    }

    public String getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public int getCalories() {
        return calories;
    }

    public float getProtein() {
        return protein;
    }

    public float getCarbs() {
        return carbs;
    }

    public float getFats() {
        return fats;
    }

    public Instant getEatenAt() {
        return eatenAt;
    }
}
