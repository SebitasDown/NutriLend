package com.nutriLens.NutriLens.infrastructure.port.out.persistence.adapter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutriLens.NutriLens.domain.model.MediaType;
import com.nutriLens.NutriLens.domain.model.NutritionProfile;
import com.nutriLens.NutriLens.domain.port.out.MealAiPort;
import com.nutriLens.NutriLens.infrastructure.config.GeminiConfig;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.adapter.gemini.dto.GeminiRequest;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.adapter.gemini.dto.GeminiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Base64;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class MealAiAdapter implements MealAiPort {

    private final RestClient restClient; // Injected from GeminiConfig
    private final GeminiConfig geminiConfig;
    private final ObjectMapper objectMapper;

    @Override
    public NutritionProfile analyze(byte[] fileByte, MediaType type) {
        String apiKey = geminiConfig.getApiKey();
        if (apiKey == null || apiKey.isBlank()) {
            log.error("API Key de Gemini no configurada. Configure 'gemini.api.key' en application.properties");
            throw new IllegalStateException("Servicio de IA no configurado");
        }

        try {
            log.info("Iniciando análisis de {} con Gemini...", type);
            String base64Data = Base64.getEncoder().encodeToString(fileByte);

            String systemPromptText;
            String mimeType;

            if (type == MediaType.IMAGE) {
                systemPromptText = """
                        Eres un experto en nutrición visual. Analizas fotos de comida y estimas macros.
                        Responde ÚNICAMENTE con un objeto JSON válido con este formato:
                        {
                          "calories": número entero,
                          "protein": número float,
                          "carbs": número float,
                          "fats": número float
                        }
                        """;
                mimeType = "image/jpeg";
            } else if (type == MediaType.AUDIO) {
                systemPromptText = """
                        Eres un experto en nutrición. Escucha la descripción de la comida en el audio y estima los macros.
                        Responde ÚNICAMENTE con un objeto JSON válido con este formato:
                        {
                          "calories": número entero,
                          "protein": número float,
                          "carbs": número float,
                          "fats": número float
                        }
                        """;
                mimeType = "audio/mp3"; // Gemini acepta mp3, wav, aac, etc.
            } else {
                log.warn("Formato no soportado para análisis automático: {}", type);
                return new NutritionProfile(0, 0, 0, 0);
            }

            // Build request
            GeminiRequest.Part promptPart = GeminiRequest.Part.text(systemPromptText);
            GeminiRequest.Part mediaPart = GeminiRequest.Part.image(mimeType, base64Data); // "image" sirve para inline
                                                                                           // data

            GeminiRequest.Content content = new GeminiRequest.Content(List.of(promptPart, mediaPart));
            GeminiRequest request = new GeminiRequest(
                    List.of(content),
                    new GeminiRequest.GenerationConfig("application/json"));

            // Execute
            GeminiResponse response = restClient.post()
                    .uri(uriBuilder -> uriBuilder
                            .path("/v1beta/models/gemini-2.0-flash:generateContent")
                            .queryParam("key", apiKey)
                            .build())
                    .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                    .body(request)
                    .retrieve()
                    .body(GeminiResponse.class);

            if (response != null && !response.candidates().isEmpty()) {
                String jsonText = response.candidates().get(0).content().parts().get(0).text();
                // Limpiar markdown si existe
                String cleanJson = jsonText.replace("```json", "").replace("```", "").trim();

                JsonNode root = objectMapper.readTree(cleanJson);
                int calories = root.path("calories").asInt(0);
                float protein = (float) root.path("protein").asDouble(0.0);
                float carbs = (float) root.path("carbs").asDouble(0.0);
                float fats = (float) root.path("fats").asDouble(0.0);

                log.info("Análisis completado: {} kcal", calories);
                return new NutritionProfile(calories, protein, carbs, fats);
            }

        } catch (Exception e) {
            log.error("Error analizando medio con Gemini", e);
            throw new RuntimeException("Error en análisis de IA: " + e.getMessage(), e);
        }

        throw new RuntimeException("La IA no devolvió ninguna sugerencia de nutrición válida.");
    }
}
