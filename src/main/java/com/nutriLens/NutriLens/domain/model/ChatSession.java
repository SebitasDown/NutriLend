package com.nutriLens.NutriLens.domain.model;

import java.time.Instant;

public class ChatSessionModel {

    private String id;
    private Long userId;
    private Instant createdAt;


    public ChatSessionModel(String id, Long userId, Instant createdAt) {
        this.id = id;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Long getUserId() {
        return userId;
    }
}
