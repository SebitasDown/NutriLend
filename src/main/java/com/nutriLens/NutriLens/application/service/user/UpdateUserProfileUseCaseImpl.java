package com.nutriLens.NutriLens.application.service.user;

import com.nutriLens.NutriLens.application.exception.NotFound;
import com.nutriLens.NutriLens.domain.model.*;
import com.nutriLens.NutriLens.domain.port.in.user.UpdateUserProfileUseCase;
import com.nutriLens.NutriLens.domain.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateUserProfileUseCaseImpl implements UpdateUserProfileUseCase {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User updateProfile(Long userId, String displayName, String avatarUrl, Float weight, Float height,
            Integer age, Preference preference, Integer meals, Goal goal, ActivityLevel activityLevel) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFound("Usuario no encontrado"));

        user.updateProfile(displayName, avatarUrl, weight, height, age, preference, meals, goal, activityLevel);

        return userRepository.save(user);
    }
}
