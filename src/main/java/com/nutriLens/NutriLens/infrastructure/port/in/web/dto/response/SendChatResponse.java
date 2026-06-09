package com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Respuesta del asistente IA")
public class SendChatResponse {
    @Schema(description = "ID de la conversación", example = "conv-abc123")
    private String conversationId;

    @Schema(description = "Respuesta del asistente", example = "Una manzana tiene aproximadamente 95 calorías.")
    private String reply;
}
