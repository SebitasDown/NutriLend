package com.nutriLens.NutriLens.domain.port.in.user;

import com.nutriLens.NutriLens.domain.model.User;
import com.nutriLens.NutriLens.domain.model.ActivityLevel;
import com.nutriLens.NutriLens.domain.model.Goal;
import com.nutriLens.NutriLens.domain.model.Preference;

public interface UpdateUserProfileUseCase {
    User updateProfile(Long userId, String displayName, String avatarUrl, Float weight, Float height, Integer age,
            Preference preference, Integer meals, Goal goal, ActivityLevel activityLevel);
}
