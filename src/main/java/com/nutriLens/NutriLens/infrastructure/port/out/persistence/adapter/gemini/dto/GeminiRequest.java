package com.nutriLens.NutriLens.infrastructure.port.out.persistence.adapter.gemini.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record GeminiRequest(
        List<Content> contents,
        @JsonProperty("systemInstruction") Content systemInstruction,
        @JsonProperty("generationConfig") GenerationConfig generationConfig) {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Content(String role, List<Part> parts) {
        public static Content user(List<Part> parts) {
            return new Content("user", parts);
        }

        public static Content model(List<Part> parts) {
            return new Content("model", parts);
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Part(String text, @JsonProperty("inline_data") InlineData inlineData) {
        public static Part text(String text) {
            return new Part(text, null);
        }

        public static Part image(String mimeType, String base64Data) {
            return new Part(null, new InlineData(mimeType, base64Data));
        }
    }

    public record InlineData(@JsonProperty("mime_type") String mimeType, String data) {
    }

    public record GenerationConfig(@JsonProperty("response_mime_type") String responseMimeType) {
    }
}
