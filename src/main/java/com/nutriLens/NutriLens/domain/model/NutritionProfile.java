package com.nutriLens.NutriLens.domain.model;

public class NutritionProfile {

    private int calories;
    private float protein;
    private float carbs;
    private float fats;

    public NutritionProfile(int calories, float protein, float carbs, float fats) {
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
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
}
