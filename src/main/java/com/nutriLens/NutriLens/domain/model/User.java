package com.nutriLens.NutriLens.domain.model;

import java.time.LocalDateTime;

public class User {

    // Usuario clave
    private Long id;
    private String displayName;
    private String email;
    private String avatarUrl; // esto a claudinary

    // Datos necesarios para el perfil
    private Float weight;
    private Float height;
    private Integer age;
    private Preference preference;
    // Cuatas comidas
    private Integer meals;

    // ToDo: Que tan claro tienes que comer en tu dia a dia

    // Perfil Nutricional
    private Goal goal;
    private ActivityLevel activityLevel;

    // Para desactivar la cuenta
    private boolean delete;

    // Auditoria
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User() {
    }

    public User(String username, String displayName, boolean delete) {
        this.displayName = displayName;
        this.delete = delete;
    }

    public void updateProfile(String displayName, String avatarUrl, Float weight, Float height, Integer age,
            Preference preference, Integer meals, Goal goal, ActivityLevel activityLevel) {
        this.displayName = displayName;
        this.avatarUrl = avatarUrl;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.preference = preference;
        this.meals = meals;
        this.goal = goal;
        this.activityLevel = activityLevel;
    }

    public void updateProfilePhoto(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public Preference getPreference() {
        return preference;
    }

    public void setPreference(Preference preference) {
        this.preference = preference;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getMeals() {
        return meals;
    }

    public void setMeals(Integer meals) {
        this.meals = meals;
    }
}
