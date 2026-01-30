package com.nutriLens.NutriLens.infrastructure.port.out.persistence.adapter;

import com.nutriLens.NutriLens.domain.model.ChatMessage;
import com.nutriLens.NutriLens.domain.port.out.ChatRepository;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.document.ChatMessageDocument;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.mapper.ChatMessageMapper;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.repository.MongoChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ChatRepositoryAdapter implements ChatRepository {

    private final MongoChatMessageRepository mongoChatMessageRepository;
    private final ChatMessageMapper chatMessageMapper;

    @Override
    public void save(ChatMessage chatMessage) {
        ChatMessageDocument document = chatMessageMapper.toDocument(chatMessage);
        mongoChatMessageRepository.save(document);
    }

    @Override
    public List<ChatMessage> findByConversationIdAndUserId(String conversationId, Long userId) {
        List<ChatMessageDocument> documents = mongoChatMessageRepository
                .findByConversationIdAndUserIdOrderByCreateAtAsc(conversationId, userId);
        
        return documents.stream()
                .map(chatMessageMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChatMessage> findRecent(String conversationId, int limit) {
        List<ChatMessageDocument> documents = mongoChatMessageRepository
                .findByConversationIdOrderByCreateAtDesc(conversationId, PageRequest.of(0, limit));
        
        return documents.stream()
                .map(chatMessageMapper::toDomain)
                .collect(Collectors.toList());
    }
}
