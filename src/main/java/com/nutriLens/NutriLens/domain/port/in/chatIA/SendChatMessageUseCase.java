package com.nutriLens.NutriLens.domain.port.in.chatIA;

public interface SendChatMessageUseCase {
    String sendMessage ( Long userId, String conversationId, String message);
}
