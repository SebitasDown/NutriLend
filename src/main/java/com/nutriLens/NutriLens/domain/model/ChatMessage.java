package com.nutriLens.NutriLens.domain.model;

import java.time.Instant;

public class ChatMessage {

    private String id;
    private String conversationId;
    private Long userId;
    private ChatRole role;
    private String content;
    private Instant createAt;

    public ChatMessage(String id, String conversationId, Long userId, ChatRole role, String content, Instant createAt) {
        this.id = id;
        this.conversationId = conversationId;
        this.userId = userId;
        this.role = role;
        this.content = content;
        this.createAt = createAt;
    }

    public ChatMessage(String conversationId, Long userId, ChatRole role, String content, Instant createAt) {
        this.conversationId = conversationId;
        this.userId = userId;
        this.role = role;
        this.content = content;
        this.createAt = createAt;
    }

    public ChatMessage() {
    }

    public ChatMessage(String conversationId, Long userId, ChatRole role, String content) {
        this(
                conversationId,
                userId,
                role,
                content,
                Instant.now()
        );
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConversationId() {
        return conversationId;
    }

    public Long getUserId() {
        return userId;
    }

    public ChatRole getRole() {
        return role;
    }

    public String getContent() {
        return content;
    }

    public Instant getCreateAt() {
        return createAt;
    }
}
