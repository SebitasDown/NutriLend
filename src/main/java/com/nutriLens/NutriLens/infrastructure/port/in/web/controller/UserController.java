package com.nutriLens.NutriLens.infrastructure.port.in.web.controller;

import com.nutriLens.NutriLens.domain.model.User;
import com.nutriLens.NutriLens.domain.port.in.auth.UserSession;
import com.nutriLens.NutriLens.domain.port.in.user.GetUserProfileUseCase;
import com.nutriLens.NutriLens.domain.port.in.user.UpdateUserProfileUseCase;
import com.nutriLens.NutriLens.application.exception.NotFound;
import com.nutriLens.NutriLens.infrastructure.port.in.web.dto.request.UserUpdateRequest;
import com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response.UserResponseDto;
import com.nutriLens.NutriLens.infrastructure.port.in.web.mapper.UserDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UpdateUserProfileUseCase updateUserProfileUseCase;
    private final GetUserProfileUseCase getUserProfileUseCase;
    private final UserDtoMapper userDtoMapper;

    @GetMapping("/profile")
    public ResponseEntity<UserResponseDto> getProfile(@AuthenticationPrincipal UserSession session) {
        User user = getUserProfileUseCase.execute(session.getUserId());
        return ResponseEntity.ok(userDtoMapper.toDto(user));
    }

    @PutMapping("/profile")
    public ResponseEntity<UserResponseDto> updateProfile(
            @AuthenticationPrincipal UserSession session,
            @Valid @RequestBody UserUpdateRequest request) {

        User updatedUser = updateUserProfileUseCase.updateProfile(
                session.getUserId(),
                request.getDisplayName(),
                request.getAvatarUrl(),
                request.getWeight(),
                request.getHeight(),
                request.getAge(),
                request.getPreference(),
                request.getMeals(),
                request.getGoal(),
                request.getActivityLevel());

        return ResponseEntity.ok(userDtoMapper.toDto(updatedUser));
    }
}
