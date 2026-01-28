package com.nutriLens.NutriLens.application.service.user;

import com.nutriLens.NutriLens.application.exception.NotFound;
import com.nutriLens.NutriLens.domain.model.User;
import com.nutriLens.NutriLens.domain.port.in.user.GetUserProfileUseCase;
import com.nutriLens.NutriLens.domain.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserProfileUseCaseImpl implements GetUserProfileUseCase {

    private final UserRepository userRepository;

    @Override
    public User execute(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFound("Usuario no encontrado"));
    }
}
