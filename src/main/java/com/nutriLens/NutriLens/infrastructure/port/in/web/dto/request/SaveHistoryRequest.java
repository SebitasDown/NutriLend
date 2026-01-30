package com.nutriLens.NutriLens.infrastructure.port.in.web.dto.request;

import com.nutriLens.NutriLens.domain.model.ChatRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos para guardar un mensaje en el historial de chat")
public class SaveHistoryRequest {
    @Schema(description = "ID único de la conversación", example = "conv-abc123", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String conversationId;

    @Schema(description = "Rol del mensaje: USER o ASSISTANT", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private ChatRole role;

    @Schema(description = "Contenido del mensaje", example = "¿Cuántas calorías tiene una manzana?", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String content;
}
