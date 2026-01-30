package com.nutriLens.NutriLens.infrastructure.port.out.persistence.adapter.gemini.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record GeminiRequest(List<Content> contents,
        @JsonProperty("generationConfig") GenerationConfig generationConfig) {

    public record Content(List<Part> parts) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Part(String text, @JsonProperty("inline_data") InlineData inlineData) {
        // Constructor de conveniencia para texto
        public static Part text(String text) {
            return new Part(text, null);
        }

        // Constructor de conveniencia para imagen
        public static Part image(String mimeType, String base64Data) {
            return new Part(null, new InlineData(mimeType, base64Data));
        }
    }

    public record InlineData(@JsonProperty("mime_type") String mimeType, String data) {
    }

    public record GenerationConfig(@JsonProperty("response_mime_type") String responseMimeType) {
    }
}
