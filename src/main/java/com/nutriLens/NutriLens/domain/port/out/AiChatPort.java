package com.nutriLens.NutriLens.domain.port.out;

import com.nutriLens.NutriLens.domain.model.ChatMessage;

import java.util.List;

// Puerto que se comunicara con el servicio para enviarle los datos a la IA
public interface AiChatPort {

    String send(String conversationId, List<ChatMessage> context);
}
