package com.nutriLens.NutriLens.domain.model;

public class MediaInput {
    private MediaType type;
    private String cloudinaryUrl;

    public MediaInput(MediaType type, String cloudinaryUrl) {
        this.type = type;
        this.cloudinaryUrl = cloudinaryUrl;
    }

    public MediaType getType() {
        return type;
    }

    public String getCloudinaryUrl() {
        return cloudinaryUrl;
    }
}
