package com.nutriLens.NutriLens.domain.model;

public class DailyNutrition {
    private int totalCalories;
    private float totalProtein;
    private float totalCarbs;
    private float totalFats;
    private int calorieGoal;
    private float calorieProgressPercentage;

    public DailyNutrition(int totalCalories, float totalProtein, float totalCarbs, float totalFats, int calorieGoal) {
        this.totalCalories = totalCalories;
        this.totalProtein = totalProtein;
        this.totalCarbs = totalCarbs;
        this.totalFats = totalFats;
        this.calorieGoal = calorieGoal;
        this.calorieProgressPercentage = calorieGoal > 0 ? (float) totalCalories / calorieGoal * 100 : 0;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public float getTotalProtein() {
        return totalProtein;
    }

    public float getTotalCarbs() {
        return totalCarbs;
    }

    public float getTotalFats() {
        return totalFats;
    }

    public int getCalorieGoal() {
        return calorieGoal;
    }

    public float getCalorieProgressPercentage() {
        return calorieProgressPercentage;
    }
}
