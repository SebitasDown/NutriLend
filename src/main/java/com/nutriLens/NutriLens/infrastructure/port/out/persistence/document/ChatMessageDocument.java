package com.nutriLens.NutriLens.infrastructure.port.out.persistence.document;

import com.nutriLens.NutriLens.domain.model.ChatRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document(collection = "chat_messages")
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDocument {

    @Id
    private String id;
    
    private String conversationId;
    private Long userId;
    private ChatRole role;
    private String content;
    private Instant createAt;
}
