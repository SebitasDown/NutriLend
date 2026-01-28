package com.nutriLens.NutriLens.domain.port.in.auth;

import com.nutriLens.NutriLens.domain.model.ActivityLevel;
import com.nutriLens.NutriLens.domain.model.Goal;
import com.nutriLens.NutriLens.domain.model.Preference;

public interface RegisterUserUseCase {
    AuthResult register(String displayName, String email, String password, Float weight, Float height,
            Integer age, Preference preference, Integer meals, Goal goal, ActivityLevel activityLevel);
}
