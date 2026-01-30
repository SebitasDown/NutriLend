package com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response;

import com.nutriLens.NutriLens.domain.model.ChatRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Mensaje de chat entre usuario y asistente IA")
public class ChatMessageResponseDto {
    @Schema(description = "ID único del mensaje", example = "msg-abc123")
    private String id;

    @Schema(description = "ID de la conversación", example = "conv-xyz789")
    private String conversationId;

    @Schema(description = "Rol del mensaje: USER o ASSISTANT")
    private ChatRole role;

    @Schema(description = "Contenido del mensaje", example = "¿Cuántas calorías tiene una manzana?")
    private String content;

    @Schema(description = "Fecha y hora de creación del mensaje", example = "2025-01-30T15:30:00Z")
    private Instant createdAt;
}
