package com.nutriLens.NutriLens.domain.port.in.user;

public interface UpdateProfilePhotoUseCase {
    String uploadProfileImage (Long userId, byte[] imageBytes);
}
