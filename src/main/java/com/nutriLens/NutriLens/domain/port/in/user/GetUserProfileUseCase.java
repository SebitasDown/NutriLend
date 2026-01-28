package com.nutriLens.NutriLens.domain.port.in.user;

import com.nutriLens.NutriLens.domain.model.User;

public interface GetUserProfileUseCase {
    User execute(Long userId);
}
