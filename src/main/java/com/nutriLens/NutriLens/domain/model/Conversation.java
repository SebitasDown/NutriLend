package com.nutriLens.NutriLens.domain.model;

import java.time.Instant;

public class Conversation {

    private String id;
    private Long userId;
    private Instant createdAt;

    public Conversation(String id, Long userId, Instant createdAt) {
        this.id = id;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
