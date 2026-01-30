package com.nutriLens.NutriLens.infrastructure.port.out.persistence.repository;

import com.nutriLens.NutriLens.infrastructure.port.out.persistence.document.ChatMessageDocument;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongoChatMessageRepository extends MongoRepository<ChatMessageDocument, String> {
    
    List<ChatMessageDocument> findByConversationIdAndUserIdOrderByCreateAtAsc(String conversationId, Long userId);
    
    List<ChatMessageDocument> findByConversationIdOrderByCreateAtDesc(String conversationId, Pageable pageable);
}
