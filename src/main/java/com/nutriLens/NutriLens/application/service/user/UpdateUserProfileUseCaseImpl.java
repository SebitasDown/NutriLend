package com.nutriLens.NutriLens.application.service;

import com.nutriLens.NutriLens.application.exception.NotFound;
import com.nutriLens.NutriLens.domain.model.User;
import com.nutriLens.NutriLens.domain.port.in.user.UpdateUserProfileCommand;
import com.nutriLens.NutriLens.domain.port.in.user.UpdateUserProfileUseCase;
import com.nutriLens.NutriLens.domain.port.out.UserRepository;

public class UpdateUserProfileUseCaseImpl implements UpdateUserProfileUseCase {

    private final UserRepository userRepository;

    public UpdateUserProfileUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(Long userId, UpdateUserProfileCommand command) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFound("Usuario no encontrado"));

        user.updateProfile(
                command.getUsername(),
                command.getDisplayName(),
                command.getAvatarUrl(),
                command.getWeight(),
                command.getHeight(),
                command.getAge(),
                command.getGoal(),
                command.getActivityLevel()
        );

        userRepository.save(user);
    }
}

