package com.nutriLens.NutriLens.infrastructure.port.in.web.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos para enviar un mensaje al asistente IA")
public class SendChatRequest {
    @Schema(description = "ID único de la conversación", example = "conv-abc123", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String conversationId;

    @Schema(description = "Mensaje del usuario", example = "¿Cuántas calorías tiene una manzana?", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String message;
}
