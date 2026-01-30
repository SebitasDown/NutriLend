package com.nutriLens.NutriLens.infrastructure.port.out.persistence.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document(collection = "conversations")
@AllArgsConstructor
@NoArgsConstructor
public class ConversationDocument {

    @Id
    private String id;
    
    private Long userId;
    private Instant createdAt;
}
