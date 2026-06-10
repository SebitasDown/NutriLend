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
            log.info("Iniciando analisis de audio ({} bytes)...", fileByte.length);
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

    @Override
    public NutritionProfile analyzeText(String description) {
        log.info("Iniciando analisis de texto...");
        try {
            return analyzeTextWithGemini(description);
        } catch (Exception e) {
            log.warn("Gemini fallo en analisis de texto, intentando con Groq: {}", e.getMessage());
            try {
                return analyzeTextWithGroq(description);
            } catch (Exception ex) {
                log.error("Ambos proveedores de IA fallaron en analisis de texto", ex);
                throw new RuntimeException("Error en analisis de texto IA: " + ex.getMessage(), ex);
            }
        }
    }

    private NutritionProfile analyzeWithGemini(byte[] fileByte, MediaType type) {
        String apiKey = geminiConfig.getApiKey();
        if (apiKey == null || apiKey.isBlank()) {
            log.warn("Gemini API key no configurada, saltando...");
            throw new IllegalStateException("Gemini no configurado");
        }

        log.info("Iniciando analisis con Gemini para {}...", type);
        String base64Data = Base64.getEncoder().encodeToString(fileByte);
        String prompt = buildPrompt(type);
        String mimeType = (type == MediaType.IMAGE) ? "image/jpeg" : "audio/mp4";

        GeminiRequest.Part promptPart = GeminiRequest.Part.text(prompt);
        GeminiRequest.Part mediaPart = GeminiRequest.Part.image(mimeType, base64Data);
        GeminiRequest.Content content = new GeminiRequest.Content("user", List.of(promptPart, mediaPart));
        GeminiRequest.GenerationConfig genConfig = (type == MediaType.IMAGE)
                ? new GeminiRequest.GenerationConfig("application/json")
                : null;
        GeminiRequest request = new GeminiRequest(
                List.of(content),
                null,
                genConfig);

        log.debug("Enviando solicitud a Gemini (prompt length: {}, base64 length: {})", prompt.length(), base64Data.length());
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
            log.debug("Respuesta de Gemini: {}", jsonText);
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

    private NutritionProfile analyzeTextWithGemini(String description) {
        String apiKey = geminiConfig.getApiKey();
        if (apiKey == null || apiKey.isBlank()) {
            log.warn("Gemini API key no configurada, saltando...");
            throw new IllegalStateException("Gemini no configurado");
        }

        log.info("Iniciando analisis de texto con Gemini...");
        String prompt = buildTextPrompt();

        GeminiRequest.Part promptPart = GeminiRequest.Part.text(prompt);
        GeminiRequest.Part descPart = GeminiRequest.Part.text(description);
        GeminiRequest.Content content = new GeminiRequest.Content("user", List.of(promptPart, descPart));
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
            log.debug("Respuesta de Gemini (texto): {}", jsonText);
            return parseNutritionJson(jsonText);
        }

        throw new RuntimeException("Gemini no devolvio sugerencias validas para el texto");
    }

    private NutritionProfile analyzeTextWithGroq(String description) {
        String apiKey = groqConfig.getApiKey();
        if (apiKey == null || apiKey.isBlank()) {
            log.warn("Groq API key no configurada, saltando...");
            throw new IllegalStateException("Groq no configurado");
        }

        log.info("Iniciando analisis de texto con Groq...");
        String prompt = buildTextPrompt();

        GroqRequest request = GroqRequest.analysis("llama-3.3-70b-versatile",
                prompt + "\n\nDescripcion de la comida: " + description);

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

        throw new RuntimeException("Groq no devolvio sugerencias validas para el texto");
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
                Eres un experto en nutricion. Escucha atentamente la descripcion de la comida en el audio y estima los macros.
                La persona describe lo que comio (ingredientes, porciones, tipo de comida). Usa esa informacion para calcular.
                Si no puedes entender el audio claramente, estima basandote en el contexto de la descripcion.
                Responde UNICAMENTE con un objeto JSON valido, sin texto adicional, con este formato exacto:
                {"calories": numero entero, "protein": numero float, "carbs": numero float, "fats": numero float}
                Ejemplo: {"calories": 450, "protein": 25.5, "carbs": 35.0, "fats": 18.0}
                """;
    }

    private String buildTextPrompt() {
        return """
                Eres un experto en nutricion. Analiza la descripcion textual de una comida y estima los macros.
                La persona describe lo que comio (ingredientes, porciones, tipo de comida). Usa esa informacion para calcular los valores nutricionales.
                Responde UNICAMENTE con un objeto JSON valido, sin texto adicional, con este formato exacto:
                {"calories": numero entero, "protein": numero float, "carbs": numero float, "fats": numero float}
                Ejemplo: {"calories": 450, "protein": 25.5, "carbs": 35.0, "fats": 18.0}
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
