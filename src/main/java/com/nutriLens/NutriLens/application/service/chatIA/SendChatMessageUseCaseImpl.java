package com.nutriLens.NutriLens.application.service.chatIA;

import com.nutriLens.NutriLens.domain.model.ChatMessage;
import com.nutriLens.NutriLens.domain.model.ChatRole;
import com.nutriLens.NutriLens.domain.model.User;
import com.nutriLens.NutriLens.domain.port.in.chatIA.SendChatMessageUseCase;
import com.nutriLens.NutriLens.domain.port.out.AiChatPort;
import com.nutriLens.NutriLens.domain.port.out.ChatRepository;
import com.nutriLens.NutriLens.domain.port.out.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SendChatMessageUseCaseImpl implements SendChatMessageUseCase {

    private final ChatRepository chatRepository;
    private final AiChatPort aiChatPort;
    private final UserRepository userRepository;

    public SendChatMessageUseCaseImpl(ChatRepository chatRepository, AiChatPort aiChatPort, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.aiChatPort = aiChatPort;
        this.userRepository = userRepository;
    }

    @Override
    public String sendMessage(Long userId, String conversationId, String message) {
        ChatMessage userMessage = new ChatMessage(
                conversationId,
                userId,
                ChatRole.USER,
                message,
                Instant.now());
        chatRepository.save(userMessage);

        List<ChatMessage> history = new ArrayList<>(chatRepository.findRecent(conversationId, 20));
        java.util.Collections.reverse(history);

        Optional<User> optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            User user = optUser.get();
            String profileInfo = buildProfilePrompt(user);
            ChatMessage systemMsg = new ChatMessage(
                    conversationId, userId, ChatRole.SYSTEM, profileInfo, Instant.now());
            history.add(0, systemMsg);
        }

        String aiReply = aiChatPort.send(conversationId, history);

        ChatMessage assistantMessage = new ChatMessage(
                conversationId,
                userId,
                ChatRole.ASSISTANT,
                aiReply);

        chatRepository.save(assistantMessage);

        return aiReply;
    }

    private String buildProfilePrompt(User user) {
        StringBuilder sb = new StringBuilder();
        sb.append("Eres NutriBot, un asistente nutricional experto. Respondes en espanol de forma clara y concisa. ");
        sb.append("Tu usuario tiene el siguiente perfil:\n");
        if (user.getDisplayName() != null) sb.append("- Nombre: ").append(user.getDisplayName()).append("\n");
        if (user.getAge() != null) sb.append("- Edad: ").append(user.getAge()).append(" anios\n");
        if (user.getWeight() != null) sb.append("- Peso: ").append(user.getWeight()).append(" kg\n");
        if (user.getHeight() != null) sb.append("- Altura: ").append(user.getHeight()).append(" cm\n");
        if (user.getGoal() != null) sb.append("- Objetivo: ").append(formatGoal(user.getGoal().name())).append("\n");
        if (user.getActivityLevel() != null) sb.append("- Nivel de actividad: ").append(formatActivity(user.getActivityLevel().name())).append("\n");
        if (user.getPreference() != null) sb.append("- Preferencia alimenticia: ").append(user.getPreference().name()).append("\n");
        sb.append("Usa esta informacion para personalizar tus respuestas y recomendaciones.");
        return sb.toString();
    }

    private String formatGoal(String goal) {
        return switch (goal) {
            case "LOSE_WEIGHT" -> "Bajar de peso";
            case "MAINTAIN_WEIGHT" -> "Mantener peso";
            case "GAIN_MUSCLE" -> "Ganar masa muscular";
            default -> goal;
        };
    }

    private String formatActivity(String activity) {
        return switch (activity) {
            case "LOW" -> "Bajo";
            case "MEDIUM" -> "Medio";
            case "HIGH" -> "Alto";
            default -> activity;
        };
    }
}
