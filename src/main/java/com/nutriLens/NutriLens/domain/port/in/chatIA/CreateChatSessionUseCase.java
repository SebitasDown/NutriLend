package com.nutriLens.NutriLens.domain.port.in.chatIA;

public interface CreateChatSessionUseCase {

    SessionResponse execute(Long userId);
}
