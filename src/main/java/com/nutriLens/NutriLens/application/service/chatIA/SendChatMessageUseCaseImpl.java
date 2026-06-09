package com.nutriLens.NutriLens.application.service.chatIA;

import com.nutriLens.NutriLens.domain.model.ChatMessage;
import com.nutriLens.NutriLens.domain.model.ChatRole;
import com.nutriLens.NutriLens.domain.model.User;
import com.nutriLens.NutriLens.domain.port.in.chatIA.SendChatMessageUseCase;
import com.nutriLens.NutriLens.domain.port.out.AiChatPort;
import com.nutriLens.NutriLens.domain.port.out.ChatRepository;
import com.nutriLens.NutriLens.domain.port.out.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        // 1. Obtener el historial previo ANTES de guardar nada nuevo
        List<ChatMessage> history = new ArrayList<>(chatRepository.findRecent(conversationId, 20));
        java.util.Collections.reverse(history);

        // 2. Verificar o crear el System Message al inicio de la lista
        boolean hasSystemMessage = history.stream().anyMatch(msg -> msg.getRole() == ChatRole.SYSTEM);

        if (!hasSystemMessage) {
            Optional<User> optUser = userRepository.findById(userId);
            if (optUser.isPresent()) {
                User user = optUser.get();
                String profileInfo = buildProfilePrompt(user);
                ChatMessage systemMsg = new ChatMessage(
                        conversationId, userId, ChatRole.SYSTEM, profileInfo, Instant.now());
                chatRepository.save(systemMsg);
                history.add(0, systemMsg); // Lo inyectamos de primero en memoria
            }
        }

        // 3. Crear y guardar el mensaje NUEVO del usuario
        ChatMessage userMessage = new ChatMessage(
                conversationId,
                userId,
                ChatRole.USER,
                message,
                Instant.now());
        chatRepository.save(userMessage);

        // 4. AGREGAR EXPLÍCITAMENTE EL MENSAJE A LA LISTA EN MEMORIA (Evita el 400 de Gemini)
        history.add(userMessage);

        // 5. Enviar el contexto completo e infalible a la IA
        String aiReply = aiChatPort.send(conversationId, history);

        // 6. Guardar la respuesta del asistente
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

        // 1. Definición del rol y contexto
        sb.append("Eres NutriBot, el asistente nutricional integrado de la app NutriLens. ");

        // 2. REGLAS ESTRICTAS (Soluciona los "holas" y las respuestas repetitivas)
        sb.append("REGLAS ESTRICTAS QUE DEBES CUMPLIR:\n");
        sb.append("- NO saludes. Cero saludos (prohibido decir 'Hola', 'Buenos días', '¿En qué te ayudo?'). Ve directo a la respuesta.\n");
        sb.append("- Estás en medio de un chat continuo. Responde de forma natural y coherente al último mensaje.\n");
        sb.append("- Sé claro, conciso y no repitas información obvia.\n\n");

        // 3. Inyección de datos del usuario
        sb.append("PERFIL DEL USUARIO ACTUAL:\n");
        if (user.getDisplayName() != null) sb.append("- Nombre: ").append(user.getDisplayName()).append("\n");
        if (user.getAge() != null) sb.append("- Edad: ").append(user.getAge()).append(" años\n");
        if (user.getWeight() != null) sb.append("- Peso: ").append(user.getWeight()).append(" kg\n");
        if (user.getHeight() != null) sb.append("- Altura: ").append(user.getHeight()).append(" cm\n");
        if (user.getGoal() != null) sb.append("- Objetivo: ").append(formatGoal(user.getGoal().name())).append("\n");
        if (user.getActivityLevel() != null) sb.append("- Nivel de actividad: ").append(formatActivity(user.getActivityLevel().name())).append("\n");
        if (user.getPreference() != null) sb.append("- Preferencia alimenticia: ").append(user.getPreference().name()).append("\n\n");

        sb.append("Usa estos datos implícitamente para personalizar la respuesta, pero no los menciones a menos que sea necesario para la explicación.");

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