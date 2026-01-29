package com.nutriLens.NutriLens.domain.port.out;

import com.nutriLens.NutriLens.domain.model.ChatMessage;

import java.util.List;

public interface ChatRepository {
    void save (ChatMessage chatMessage);

    List<ChatMessage> findByConversationIdAndUserId (String conversationId, Long userId);

    List<ChatMessage> findRecent(String conversationId, int limit);
}
