package com.nutriLens.NutriLens.application.service.chatIA;

import com.nutriLens.NutriLens.domain.model.ChatMessage;
import com.nutriLens.NutriLens.domain.port.in.chatIA.GetConversationHistoryUseCase;
import com.nutriLens.NutriLens.domain.port.out.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetConversationHistoryUseCaseImpl implements GetConversationHistoryUseCase {

    private final ChatRepository chatRepository;

    public GetConversationHistoryUseCaseImpl(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public List<ChatMessage> getHistory(Long userId, String conversationId) {
        return chatRepository.findByConversationIdAndUserId(conversationId, userId);
    }
}
