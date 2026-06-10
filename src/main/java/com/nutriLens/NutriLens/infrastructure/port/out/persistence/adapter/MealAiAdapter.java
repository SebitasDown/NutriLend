package com.nutriLens.NutriLens.infrastructure.port.out.persistence.adapter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutriLens.NutriLens.domain.model.MediaType;
import com.nutriLens.NutriLens.domain.model.NutritionProfile;
import com.nutriLens.NutriLens.domain.port.out.MealAiPort;
import com.nutriLens.NutriLens.infrastructure.config.GeminiConfig;
import com.nutriLens.NutriLens.infrastructure.config.GroqConfig;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.adapter.gemini.dto.GeminiRequest;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.adapter.gemini.dto.GeminiResponse;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.adapter.groq.dto.GroqRequest;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.adapter.groq.dto.GroqResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Base64;
import java.util.List;

@Slf4j
@Component
public class MealAiAdapter implements MealAiPort {

    private final RestClient geminiRestClient;
    private final RestClient groqRestClient;
    private final GeminiConfig geminiConfig;
    private final GroqConfig groqConfig;
    private final ObjectMapper objectMapper;

    public MealAiAdapter(
            @Qualifier("geminiRestClient") RestClient geminiRestClient,
            @Qualifier("groqRestClient") RestClient groqRestClient,
            GeminiConfig geminiConfig,
            GroqConfig groqConfig,
            ObjectMapper objectMapper) {
        this.geminiRestClient = geminiRestClient;
        this.groqRestClient = groqRestClient;
        this.geminiConfig = geminiConfig;
        this.groqConfig = groqConfig;
        this.objectMapper = objectMapper;
    }

    @Override
    public NutritionProfile analyze(byte[] fileByte, MediaType type) {
        if (type == MediaType.AUDIO) {
            return analyzeWithGemini(fileByte, type);
        }
        try {
            return analyzeWithGemini(fileByte, type);
        } catch (Exception e) {
            log.warn("Gemini fallo, intentando con Groq: {}", e.getMessage());
            try {
                return analyzeWithGroq(fileByte, type);
            } catch (Exception ex) {
                log.error("Ambos proveedores de IA fallaron", ex);
                throw new RuntimeException("Error en analisis de IA: " + ex.getMessage(), ex);
            }
        }
    }

    private NutritionProfile analyzeWithGemini(byte[] fileByte, MediaType type) {
        String apiKey = geminiConfig.getApiKey();
        if (apiKey == null || apiKey.isBlank()) {
            log.warn("Gemini API key no configurada, saltando...");
            throw new IllegalStateException("Gemini no configurado");
        }

        log.info("Iniciando analisis con Gemini...");
        String base64Data = Base64.getEncoder().encodeToString(fileByte);
        String prompt = buildPrompt(type);
        String mimeType = (type == MediaType.IMAGE) ? "image/jpeg" : "audio/mp4";

        GeminiRequest.Part promptPart = GeminiRequest.Part.text(prompt);
        GeminiRequest.Part mediaPart = GeminiRequest.Part.image(mimeType, base64Data);
        GeminiRequest.Content content = new GeminiRequest.Content("user", List.of(promptPart, mediaPart));
        GeminiRequest request = new GeminiRequest(
                List.of(content),
                null,
                new GeminiRequest.GenerationConfig("application/json"));

        GeminiResponse response = geminiRestClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1beta/models/gemini-2.5-flash-lite:generateContent")
                        .queryParam("key", apiKey)
                        .build())
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(GeminiResponse.class);

        if (response != null && !response.candidates().isEmpty()) {
            String jsonText = response.candidates().get(0).content().parts().get(0).text();
            return parseNutritionJson(jsonText);
        }

        throw new RuntimeException("Gemini no devolvio sugerencias validas");
    }

    private NutritionProfile analyzeWithGroq(byte[] fileByte, MediaType type) {
        String apiKey = groqConfig.getApiKey();
        if (apiKey == null || apiKey.isBlank()) {
            log.warn("Groq API key no configurada, saltando...");
            throw new IllegalStateException("Groq no configurado");
        }

        log.info("Iniciando analisis con Groq...");
        String base64Data = Base64.getEncoder().encodeToString(fileByte);
        String prompt = buildPrompt(type);

        GroqRequest request = GroqRequest.analysis("llama-3.3-70b-versatile",
                prompt + "\n\nDatos del archivo (base64): " + base64Data);

        GroqResponse response = groqRestClient.post()
                .uri("/chat/completions")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(GroqResponse.class);

        if (response != null && !response.choices().isEmpty()) {
            String content = response.choices().get(0).message().content();
            return parseNutritionJson(content);
        }

        throw new RuntimeException("Groq no devolvio sugerencias validas");
    }

    private String buildPrompt(MediaType type) {
        if (type == MediaType.IMAGE) {
            return """
                    Eres un experto en nutricion visual. Analizas fotos de comida y estimas macros.
                    Responde UNICAMENTE con un objeto JSON valido con este formato:
                    {"calories": numero entero, "protein": numero float, "carbs": numero float, "fats": numero float}
                    """;
        }
        return """
                Eres un experto en nutricion. Escucha la descripcion de la comida en el audio y estima los macros.
                Responde UNICAMENTE con un objeto JSON valido con este formato:
                {"calories": numero entero, "protein": numero float, "carbs": numero float, "fats": numero float}
                """;
    }

    private NutritionProfile parseNutritionJson(String jsonText) {
        try {
            String cleanJson = jsonText.replace("```json", "").replace("```", "").trim();
            JsonNode root = objectMapper.readTree(cleanJson);
            int calories = root.path("calories").asInt(0);
            float protein = (float) root.path("protein").asDouble(0.0);
            float carbs = (float) root.path("carbs").asDouble(0.0);
            float fats = (float) root.path("fats").asDouble(0.0);
            log.info("Analisis completado: {} kcal", calories);
            return new NutritionProfile(calories, protein, carbs, fats);
        } catch (Exception e) {
            log.error("Error parseando JSON de IA: {}", jsonText, e);
            throw new RuntimeException("Error parseando respuesta de IA", e);
        }
    }
}
