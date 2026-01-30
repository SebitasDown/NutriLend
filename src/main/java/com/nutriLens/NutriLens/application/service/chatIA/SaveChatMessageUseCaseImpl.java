package com.nutriLens.NutriLens.application.service.chatIA;

import com.nutriLens.NutriLens.domain.model.ChatMessage;
import com.nutriLens.NutriLens.domain.model.ChatRole;
import com.nutriLens.NutriLens.domain.port.in.chatIA.SaveChatMessageUseCase;
import com.nutriLens.NutriLens.domain.port.out.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class SaveChatMessageUseCaseImpl implements SaveChatMessageUseCase {

    private final ChatRepository chatRepository;

    @Override
    public void saveMessage(Long userId, String conversationId, ChatRole role, String content) {
        ChatMessage message = new ChatMessage(
                conversationId,
                userId,
                role,
                content,
                Instant.now());
        chatRepository.save(message);
    }
}
