package com.nutriLens.NutriLens.infrastructure.port.out.persistence.mapper;

import com.nutriLens.NutriLens.domain.model.ChatMessage;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.document.ChatMessageDocument;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ChatMessageMapper {

    ChatMessageDocument toDocument(ChatMessage chatMessage);

    ChatMessage toDomain(ChatMessageDocument document);
}
