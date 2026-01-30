package com.nutriLens.NutriLens.infrastructure.port.out.persistence.adapter;

import com.nutriLens.NutriLens.domain.model.ChatMessage;
import com.nutriLens.NutriLens.domain.port.out.AiChatPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class AiChatAdapter implements AiChatPort {

    // TODO: Implementar integración con servicio de IA (OpenAI, Gemini, etc.)
    // Este es un stub temporal para que la aplicación pueda iniciar

    @Override
    public String send(String conversationId, List<ChatMessage> context) {
        log.warn("AiChatAdapter.send() - Implementación stub. Conversación: {}, Mensajes: {}", 
                conversationId, context.size());
        
        // Respuesta temporal de ejemplo
        return "Esta es una respuesta temporal. Por favor implementa la integración con el servicio de IA.";
    }
}
