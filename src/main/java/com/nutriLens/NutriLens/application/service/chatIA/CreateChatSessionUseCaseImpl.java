package com.nutriLens.NutriLens.application.service.chatIA;

import com.nutriLens.NutriLens.domain.model.ChatSession;
import com.nutriLens.NutriLens.domain.port.in.chatIA.CreateChatSessionUseCase;
import com.nutriLens.NutriLens.domain.port.in.chatIA.SessionResponse;
import com.nutriLens.NutriLens.domain.port.out.ChatRepository;
import com.nutriLens.NutriLens.domain.port.out.ChatSessionPort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class CreateChatSessionUseCaseImpl implements CreateChatSessionUseCase {

    private final ChatSessionPort chatSessionPort;

    public CreateChatSessionUseCaseImpl(ChatSessionPort chatSessionPort) {
        this.chatSessionPort = chatSessionPort;
    }

    @Override
    public SessionResponse execute(Long userId) {
        String sessionId = "conv_" + UUID.randomUUID().toString().replace("-","").substring(0, 16);

        ChatSession newSession = new ChatSession(sessionId, userId, Instant.now());
        chatSessionPort.save(newSession);
        return new SessionResponse(sessionId);
    }
}
