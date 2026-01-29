package com.nutriLens.NutriLens.application.service.user;

import com.nutriLens.NutriLens.application.exception.NotFound;
import com.nutriLens.NutriLens.domain.model.User;
import com.nutriLens.NutriLens.domain.port.in.user.UpdateProfilePhotoUseCase;
import com.nutriLens.NutriLens.domain.port.out.ImageStoragePort;
import com.nutriLens.NutriLens.domain.port.out.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateProfilePhotoUseCaseImpl implements UpdateProfilePhotoUseCase {

    private final UserRepository userRepository;
    private final ImageStoragePort imageStoragePort;

    public UpdateProfilePhotoUseCaseImpl(UserRepository userRepository, ImageStoragePort imageStoragePort) {
        this.userRepository = userRepository;
        this.imageStoragePort = imageStoragePort;
    }

    // Actualiza la foto de perfil
    @Override
    public String uploadProfileImage(Long userId ,byte[] imageBytes) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFound("Usuario no encontrado"));

        String imageUrl = imageStoragePort.uploadProfileImage(imageBytes);

        user.updateProfilePhoto(imageUrl);

        userRepository.save(user);

        return imageUrl;
    }
}
