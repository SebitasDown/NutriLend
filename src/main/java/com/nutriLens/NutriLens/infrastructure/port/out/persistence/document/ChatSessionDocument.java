package com.nutriLens.NutriLens.infrastructure.port.out.persistence.document;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collation = "chat_sessions")
public class ChatSessionDocument {

    @Id
    private String id;
    private Long userId;
    private Instant createdAt;

    public ChatSessionDocument(String id, Long userId, Instant createdAt) {
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
