package com.nutriLens.NutriLens.infrastructure.port.in.web.controller;

import com.nutriLens.NutriLens.domain.model.ChatMessage;
import com.nutriLens.NutriLens.domain.port.in.auth.UserSession;
import com.nutriLens.NutriLens.domain.port.in.chatIA.GetConversationHistoryUseCase;
import com.nutriLens.NutriLens.domain.port.in.chatIA.SaveChatMessageUseCase;
import com.nutriLens.NutriLens.infrastructure.port.in.web.dto.request.SaveHistoryRequest;
import com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response.ChatMessageResponseDto;
import com.nutriLens.NutriLens.infrastructure.port.in.web.mapper.ChatDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
@Tag(name = "Chat e Historial", description = "Endpoints para gestionar conversaciones y historial de chat con IA")
public class HistoryController {

    private final SaveChatMessageUseCase saveChatMessageUseCase;
    private final GetConversationHistoryUseCase getConversationHistoryUseCase;
    private final ChatDtoMapper chatDtoMapper;

    @Operation(
            summary = "Guardar mensaje de chat",
            description = "Guarda un mensaje en el historial de una conversación"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mensaje guardado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "401", description = "No autorizado - Token JWT inválido o expirado")
    })
    @PostMapping("/history")
    public ResponseEntity<Void> saveMessage(
            @Parameter(hidden = true) @AuthenticationPrincipal UserSession session,
            @Valid @RequestBody SaveHistoryRequest request) {

        saveChatMessageUseCase.saveMessage(
                session.getUserId(),
                request.getConversationId(),
                request.getRole(),
                request.getContent());

        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Obtener historial de conversación",
            description = "Recupera todos los mensajes de una conversación específica"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Historial obtenido exitosamente",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ChatMessageResponseDto.class)))),
            @ApiResponse(responseCode = "401", description = "No autorizado - Token JWT inválido o expirado"),
            @ApiResponse(responseCode = "404", description = "Conversación no encontrada")
    })
    @GetMapping("/history/{conversationId}")
    public ResponseEntity<List<ChatMessageResponseDto>> getHistory(
            @Parameter(hidden = true) @AuthenticationPrincipal UserSession session,
            @Parameter(description = "ID único de la conversación", required = true, example = "conv-123456")
            @PathVariable String conversationId) {

        List<ChatMessage> history = getConversationHistoryUseCase.getHistory(
                session.getUserId(),
                conversationId);

        return ResponseEntity.ok(chatDtoMapper.toDtoList(history));
    }
}
