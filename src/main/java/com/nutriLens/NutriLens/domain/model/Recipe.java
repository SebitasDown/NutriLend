package com.nutriLens.NutriLens.domain.model;

import java.util.List;

public class Recipe {

    private String id;
    private TypeFood typeFood;
    private String name;
    private int time;
    private String description;
    private int portion;
    private int calories;
    private String image;
    private List<Ingredient> ingredients;
    private List<String> steps;

    // Vegetariano / no vegetariano
    private boolean vegetarian;

    public Recipe(String id, TypeFood typeFood, String name, int time, String description, int portion, int calories,
            String image, List<Ingredient> ingredients, List<String> steps) {
        this.id = id;
        this.typeFood = typeFood;
        this.name = name;
        this.time = time;
        this.description = description;
        this.portion = portion;
        this.calories = calories;
        this.image = image;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TypeFood getTypeFood() {
        return typeFood;
    }

    public void setTypeFood(TypeFood typeFood) {
        this.typeFood = typeFood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPortion() {
        return portion;
    }

    public void setPortion(int portion) {
        this.portion = portion;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }
}
