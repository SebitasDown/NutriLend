package com.nutriLens.NutriLens.infrastructure.port.out.persistence.adapter;

import com.nutriLens.NutriLens.domain.model.ChatMessage;
import com.nutriLens.NutriLens.domain.port.out.AiChatPort;
import com.nutriLens.NutriLens.infrastructure.config.GeminiConfig;
import com.nutriLens.NutriLens.infrastructure.config.GroqConfig;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.adapter.gemini.dto.GeminiRequest;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.adapter.gemini.dto.GeminiResponse;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.adapter.groq.dto.GroqRequest;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.adapter.groq.dto.GroqResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AiChatAdapter implements AiChatPort {

    private final RestClient geminiRestClient;
    private final RestClient groqRestClient;
    private final GeminiConfig geminiConfig;
    private final GroqConfig groqConfig;

    public AiChatAdapter(
            @Qualifier("geminiRestClient") RestClient geminiRestClient,
            @Qualifier("groqRestClient") RestClient groqRestClient,
            GeminiConfig geminiConfig,
            GroqConfig groqConfig) {
        this.geminiRestClient = geminiRestClient;
        this.groqRestClient = groqRestClient;
        this.geminiConfig = geminiConfig;
        this.groqConfig = groqConfig;
    }

    @Override
    public String send(String conversationId, List<ChatMessage> context) {
        try {
            // Intento principal con Groq (más rápido para modelos grandes)
            return sendWithGroq(context);
        } catch (Exception e) {
            log.warn("Groq falló, intentando con Gemini: {}", e.getMessage());
            try {
                // Fallback automático a Gemini si Groq falla o se cae
                return sendWithGemini(context);
            } catch (Exception ex) {
                log.error("Ambos proveedores de IA fallaron para el chat", ex);
                return "Lo siento, en este momento no puedo procesar tu solicitud. Intenta de nuevo más tarde.";
            }
        }
    }

    private String sendWithGroq(List<ChatMessage> context) {
        String apiKey = groqConfig.getApiKey();
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("Groq no configurado");
        }

        // Groq acepta el rol 'system' directamente en la lista de mensajes
        List<GroqRequest.Message> messages = context.stream()
                .filter(msg -> msg.getRole() != null)
                .map(msg -> new GroqRequest.Message(
                        msg.getRole().name().toLowerCase(),
                        msg.getContent()))
                .collect(Collectors.toList());

        // Se usa el modelo actualizado para evitar el error 400 de modelo obsoleto
        GroqRequest request = GroqRequest.chat("llama-3.3-70b-versatile", messages);

        GroqResponse response = groqRestClient.post()
                .uri("/chat/completions")
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(GroqResponse.class);

        if (response != null && !response.choices().isEmpty()) {
            String reply = response.choices().get(0).message().content();
            log.info("Groq respondió exitosamente");
            return reply;
        }

        throw new RuntimeException("Groq no devolvió respuesta");
    }

    private String sendWithGemini(List<ChatMessage> context) {
        String apiKey = geminiConfig.getApiKey();
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("Gemini no configurado");
        }

        GeminiRequest.Content systemInstruction = null;
        List<GeminiRequest.Content> contents = new ArrayList<>();

        // Gemini requiere separar las instrucciones del sistema del resto del historial
        for (ChatMessage msg : context) {
            if (msg.getRole() == null) continue;

            GeminiRequest.Part part = GeminiRequest.Part.text(msg.getContent());

            switch (msg.getRole()) {
                case SYSTEM:
                    // Se extrae el system prompt para mandarlo en su campo específico
                    systemInstruction = new GeminiRequest.Content(null, List.of(part));
                    break;
                case USER:
                    contents.add(GeminiRequest.Content.user(List.of(part)));
                    break;
                case ASSISTANT:
                    // Mapeo correcto: en Gemini la IA tiene el rol de 'model'
                    contents.add(GeminiRequest.Content.model(List.of(part)));
                    break;
            }
        }

        GeminiRequest request = new GeminiRequest(contents, systemInstruction, null);

        GeminiResponse response = geminiRestClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1beta/models/gemini-2.5-flash-lite:generateContent")
                        .queryParam("key", apiKey)
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(GeminiResponse.class);

        if (response != null && !response.candidates().isEmpty()) {
            String reply = response.candidates().get(0).content().parts().get(0).text();
            log.info("Gemini respondió exitosamente");
            return reply;
        }

        throw new RuntimeException("Gemini no devolvió respuesta");
    }
}