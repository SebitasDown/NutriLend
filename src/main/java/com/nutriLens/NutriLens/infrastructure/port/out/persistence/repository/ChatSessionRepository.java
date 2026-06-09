package com.nutriLens.NutriLens.infrastructure.port.out.persistence.repository;

import com.nutriLens.NutriLens.infrastructure.port.out.persistence.document.ChatSessionDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatSessionRepository extends MongoRepository<ChatSessionDocument, String> {
}
