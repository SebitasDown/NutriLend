package com.nutriLens.NutriLens.application.service.chatIA;

import com.nutriLens.NutriLens.domain.model.ChatMessage;
import com.nutriLens.NutriLens.domain.model.ChatRole;
import com.nutriLens.NutriLens.domain.port.in.chatIA.SendChatMessageUseCase;
import com.nutriLens.NutriLens.domain.port.out.AiChatPort;
import com.nutriLens.NutriLens.domain.port.out.ChatRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class SendChatMessageUseCaseImpl implements SendChatMessageUseCase {

    private final ChatRepository chatRepository;
    private final AiChatPort aiChatPort;

    public SendChatMessageUseCaseImpl(ChatRepository chatRepository, AiChatPort aiChatPort) {
        this.chatRepository = chatRepository;
        this.aiChatPort = aiChatPort;
    }

    @Override
    public String sendMessage(Long userId, String conversationId, String message) {
        ChatMessage userMessage = new ChatMessage(
                conversationId,
                userId,
                ChatRole.USER,
                message,
                Instant.now());
        chatRepository.save(userMessage);

        // Obtiene el contexto del chat
        var history = chatRepository.findRecent(conversationId, 20);

        // envio al microservicio de ia
        String aiReply = aiChatPort.send(conversationId, history);

        ChatMessage assistantMessage = new ChatMessage(
                conversationId,
                userId,
                ChatRole.ASSISTANT,
                aiReply);

        chatRepository.save(assistantMessage);

        return aiReply;

    }
}
