package com.nutriLens.NutriLens.infrastructure.port.in.web.controller;

import com.nutriLens.NutriLens.domain.model.ChatMessage;
import com.nutriLens.NutriLens.domain.port.in.auth.UserSession;
import com.nutriLens.NutriLens.domain.port.in.chatIA.GetConversationHistoryUseCase;
import com.nutriLens.NutriLens.domain.port.in.chatIA.SaveChatMessageUseCase;
import com.nutriLens.NutriLens.infrastructure.port.in.web.dto.request.SaveHistoryRequest;
import com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response.ChatMessageResponseDto;
import com.nutriLens.NutriLens.infrastructure.port.in.web.mapper.ChatDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class HistoryController {

    private final SaveChatMessageUseCase saveChatMessageUseCase;
    private final GetConversationHistoryUseCase getConversationHistoryUseCase;
    private final ChatDtoMapper chatDtoMapper;

    @PostMapping("/history")
    public ResponseEntity<Void> saveMessage(
            @AuthenticationPrincipal UserSession session,
            @Valid @RequestBody SaveHistoryRequest request) {

        saveChatMessageUseCase.saveMessage(
                session.getUserId(),
                request.getConversationId(),
                request.getRole(),
                request.getContent());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/history/{conversationId}")
    public ResponseEntity<List<ChatMessageResponseDto>> getHistory(
            @AuthenticationPrincipal UserSession session,
            @PathVariable String conversationId) {

        List<ChatMessage> history = getConversationHistoryUseCase.getHistory(
                session.getUserId(),
                conversationId);

        return ResponseEntity.ok(chatDtoMapper.toDtoList(history));
    }
}
