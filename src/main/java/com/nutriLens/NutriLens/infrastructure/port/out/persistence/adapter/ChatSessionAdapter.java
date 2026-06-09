package com.nutriLens.NutriLens.infrastructure.port.out.persistence.adapter;

import com.nutriLens.NutriLens.domain.model.ChatSession;
import com.nutriLens.NutriLens.domain.port.out.ChatSessionPort;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.document.ChatSessionDocument;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.repository.ChatSessionRepository;
import org.springframework.stereotype.Component;

@Component
public class ChatSessionAdapter implements ChatSessionPort {

    private final ChatSessionRepository chatSessionRepository;

    public ChatSessionAdapter(ChatSessionRepository chatSessionRepository) {
        this.chatSessionRepository = chatSessionRepository;
    }

    @Override
    public void save(ChatSession session) {
        ChatSessionDocument document = new ChatSessionDocument(
                session.getId(),
                session.getUserId(),
                session.getCreatedAt()
        );
        chatSessionRepository.save(document);
    }
}
