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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Autenticación", description = "Endpoints para registro, login y autenticación de usuarios")
public class AuthController {

    private final LoginUserUseCase loginUserUseCase;
    private final RegisterUserUseCase registerUserUseCase;
    private final LoginWithGoogleUseCase loginWithGoogleUseCase;
    private final AuthDtoMapper authDtoMapper;

    @Operation(
            summary = "Iniciar sesión",
            description = "Autentica un usuario con email y contraseña, retornando tokens JWT de acceso y refresco"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login exitoso",
                    content = @Content(schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "401", description = "Credenciales incorrectas")
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResult result = loginUserUseCase.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(authDtoMapper.toDto(result));
    }

    @Operation(
            summary = "Registrar usuario",
            description = "Crea una nueva cuenta de usuario con perfil nutricional completo"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro exitoso",
                    content = @Content(schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "409", description = "El email ya está registrado")
    })
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

    @Operation(
            summary = "Login con Google",
            description = "Autentica o registra un usuario mediante su cuenta de Google"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticación exitosa",
                    content = @Content(schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "400", description = "Datos de Google inválidos")
    })
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
