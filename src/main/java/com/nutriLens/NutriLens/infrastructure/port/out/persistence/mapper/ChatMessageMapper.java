package com.nutriLens.NutriLens.infrastructure.port.out.persistence.mapper;

import com.nutriLens.NutriLens.domain.model.ChatMessage;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.document.ChatMessageDocument;
import org.springframework.stereotype.Component;

@Component
public class ChatMessageMapper {

    public ChatMessageDocument toDocument(ChatMessage chatMessage) {
        if (chatMessage == null) {
            return null;
        }
        return new ChatMessageDocument(
                chatMessage.getId(),
                chatMessage.getConversationId(),
                chatMessage.getUserId(),
                chatMessage.getRole(),
                chatMessage.getContent(),
                chatMessage.getCreateAt()
        );
    }

    public ChatMessage toDomain(ChatMessageDocument document) {
        if (document == null) {
            return null;
        }
        return new ChatMessage(
                document.getId(),
                document.getConversationId(),
                document.getUserId(),
                document.getRole(),
                document.getContent(),
                document.getCreateAt()
        );
    }
}