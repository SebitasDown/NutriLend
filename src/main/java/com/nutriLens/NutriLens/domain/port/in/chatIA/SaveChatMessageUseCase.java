package com.nutriLens.NutriLens.domain.port.in.chatIA;

import com.nutriLens.NutriLens.domain.model.ChatRole;

public interface SaveChatMessageUseCase {
    void saveMessage(Long userId, String conversationId, ChatRole role, String content);
}
