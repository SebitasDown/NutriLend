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
            return sendWithGroq(context);
        } catch (Exception e) {
            log.warn("Groq fallo, intentando con Gemini: {}", e.getMessage());
            try {
                return sendWithGemini(context);
            } catch (Exception ex) {
                log.error("Ambos proveedores de IA fallaron para el chat", ex);
                return "Lo siento, en este momento no puedo procesar tu solicitud. Intenta de nuevo mas tarde.";
            }
        }
    }

    private String sendWithGroq(List<ChatMessage> context) {
        String apiKey = groqConfig.getApiKey();
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("Groq no configurado");
        }

        List<GroqRequest.Message> messages = context.stream()
                .filter(msg -> msg.getRole() != null)
                .map(msg -> new GroqRequest.Message(
                        msg.getRole().name().toLowerCase(),
                        msg.getContent()))
                .collect(Collectors.toList());

        messages.add(0, GroqRequest.Message.system(
                "Eres NutriBot, un asistente nutricional experto. Respondes en espanol de forma clara y concisa. " +
                "Ayudas con preguntas sobre nutricion, dietas, calorias, recetas saludables y consejos alimenticios."));

        GroqRequest request = GroqRequest.chat("llama3-70b-8192", messages);

        GroqResponse response = groqRestClient.post()
                .uri("/chat/completions")
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(GroqResponse.class);

        if (response != null && !response.choices().isEmpty()) {
            String reply = response.choices().get(0).message().content();
            log.info("Groq respondio exitosamente");
            return reply;
        }

        throw new RuntimeException("Groq no devolvio respuesta");
    }

    private String sendWithGemini(List<ChatMessage> context) {
        String apiKey = geminiConfig.getApiKey();
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("Gemini no configurado");
        }

        String historyText = context.stream()
                .filter(msg -> msg.getRole() != null)
                .map(msg -> msg.getRole().name() + ": " + msg.getContent())
                .collect(Collectors.joining("\n"));

        String systemPrompt = "Eres NutriBot, un asistente nutricional experto. Respondes en espanol.";
        String fullPrompt = systemPrompt + "\n\nHistorial:\n" + historyText;

        GeminiRequest.Part part = GeminiRequest.Part.text(fullPrompt);
        GeminiRequest.Content content = new GeminiRequest.Content(List.of(part));
        GeminiRequest request = new GeminiRequest(List.of(content), null);

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
            log.info("Gemini respondio exitosamente");
            return reply;
        }

        throw new RuntimeException("Gemini no devolvio respuesta");
    }
}
