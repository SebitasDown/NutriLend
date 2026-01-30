package com.nutriLens.NutriLens.infrastructure.port.out.persistence.mapper;

import com.nutriLens.NutriLens.domain.model.Conversation;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.document.ConversationDocument;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ConversationMapper {

    ConversationDocument toDocument(Conversation conversation);

    Conversation toDomain(ConversationDocument document);
}
