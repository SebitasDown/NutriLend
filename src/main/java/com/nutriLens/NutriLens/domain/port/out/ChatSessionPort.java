package com.nutriLens.NutriLens.domain.port.out;

import com.nutriLens.NutriLens.domain.model.ChatSession;

public interface ChatSessionPort {
    void save (ChatSession session);
}
