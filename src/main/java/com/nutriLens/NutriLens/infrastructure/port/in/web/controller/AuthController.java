package com.nutriLens.NutriLens.infrastructure.port.in.web.controller;

import com.nutriLens.NutriLens.domain.port.in.auth.AuthResult;
import com.nutriLens.NutriLens.domain.port.in.auth.LoginUserUseCase;
import com.nutriLens.NutriLens.domain.port.in.auth.LoginWithGoogleUseCase;
import com.nutriLens.NutriLens.domain.port.in.auth.RegisterUserUseCase;
import com.nutriLens.NutriLens.infrastructure.port.in.web.dto.request.GoogleLoginRequest;
import com.nutriLens.NutriLens.infrastructure.port.in.web.dto.request.LoginRequest;
import com.nutriLens.NutriLens.infrastructure.port.in.web.dto.request.RegisterRequest;
import com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response.AuthResponse;
import com.nutriLens.NutriLens.infrastructure.port.in.web.mapper.AuthDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final LoginUserUseCase loginUserUseCase;
    private final RegisterUserUseCase registerUserUseCase;
    private final LoginWithGoogleUseCase loginWithGoogleUseCase;
    private final AuthDtoMapper authDtoMapper;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResult result = loginUserUseCase.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(authDtoMapper.toDto(result));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResult result = registerUserUseCase.register(
                request.getDisplayName(),
                request.getEmail(),
                request.getPassword(),
                request.getWeight(),
                request.getHeight(),
                request.getAge(),
                request.getPreference(),
                request.getMeals(),
                request.getGoal(),
                request.getActivityLevel());
        return ResponseEntity.ok(authDtoMapper.toDto(result));
    }

    @PostMapping("/google")
    public ResponseEntity<AuthResponse> loginWithGoogle(@Valid @RequestBody GoogleLoginRequest request) {
        AuthResult result = loginWithGoogleUseCase.loginWithGoogle(
                request.getGoogleSub(),
                request.getEmail(),
                request.getName(),
                request.getAvatarUrl());
        return ResponseEntity.ok(authDtoMapper.toDto(result));
    }
}
