package com.nutriLens.NutriLens.domain.port.in.user;

public interface UpdateUserProfileUseCase {
    void execute(Long userId, UpdateUserProfileCommand command);
}
