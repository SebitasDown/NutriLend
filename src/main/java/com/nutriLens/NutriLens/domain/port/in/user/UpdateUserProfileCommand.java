package com.nutriLens.NutriLens.domain.port.in.user;

import com.nutriLens.NutriLens.domain.model.ActivityLevel;
import com.nutriLens.NutriLens.domain.model.Goal;
import com.nutriLens.NutriLens.domain.model.Preference;

public class UpdateUserProfileCommand {
    private String displayName;
    private Integer age;
    private Float height;
    private Float weight;
    private Goal goal;
    private ActivityLevel activityLevel;
    private String avatarUrl;
    private Integer meals;
    private Preference preference;

    public UpdateUserProfileCommand(String displayName, Integer age, Float height, Float weight, Goal goal, ActivityLevel activityLevel, String avatarUrl, Integer meals, Preference preference) {
        this.displayName = displayName;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.goal = goal;
        this.activityLevel = activityLevel;
        this.avatarUrl = avatarUrl;
        this.meals = meals;
        this.preference = preference;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public ActivityLevel getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(ActivityLevel activityLevel) {
        this.activityLevel = activityLevel;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getMeals() {
        return meals;
    }

    public void setMeals(Integer meals) {
        this.meals = meals;
    }

    public Preference getPreference() {
        return preference;
    }

    public void setPreference(Preference preference) {
        this.preference = preference;
    }
}
