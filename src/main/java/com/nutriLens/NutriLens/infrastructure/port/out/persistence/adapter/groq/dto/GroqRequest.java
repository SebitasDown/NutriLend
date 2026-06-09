package com.nutriLens.NutriLens.infrastructure.port.out.persistence.adapter.groq.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record GroqRequest(
        String model,
        List<Message> messages,
        Double temperature,
        Integer maxTokens
) {
    public record Message(String role, String content) {
        public static Message system(String content) {
            return new Message("system", content);
        }
        public static Message user(String content) {
            return new Message("user", content);
        }
        public static Message assistant(String content) {
            return new Message("assistant", content);
        }
    }

    public static GroqRequest chat(String model, List<Message> messages) {
        return new GroqRequest(model, messages, 0.7, 1024);
    }

    public static GroqRequest analysis(String model, String prompt) {
        return new GroqRequest(model, List.of(
                Message.system("Eres un experto en nutricion. Responde UNICAMENTE con JSON valido."),
                Message.user(prompt)
        ), 0.3, 512);
    }
}
