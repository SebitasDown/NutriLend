package com.nutriLens.NutriLens.domain.port.in.chatIA;

import com.nutriLens.NutriLens.domain.model.ChatMessage;

import java.util.List;

public interface GetConversationHistoryUseCase {
    List<ChatMessage> getHistory (Long userId, String conversationId);
}
