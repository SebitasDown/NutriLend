package com.nutriLens.NutriLens.infrastructure.port.in.web.mapper;

import com.nutriLens.NutriLens.domain.model.ChatMessage;
import com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response.ChatMessageResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChatDtoMapper {

    public ChatMessageResponseDto toDto(ChatMessage message) {
        if (message == null)
            return null;
        return new ChatMessageResponseDto(
                message.getId(),
                message.getConversationId(),
                message.getRole(),
                message.getContent(),
                message.getCreateAt());
    }

    public List<ChatMessageResponseDto> toDtoList(List<ChatMessage> messages) {
        return messages.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
