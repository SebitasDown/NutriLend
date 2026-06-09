package com.nutriLens.NutriLens.infrastructure.port.in.web.controller;

import com.nutriLens.NutriLens.application.service.EmailService;
import com.nutriLens.NutriLens.domain.model.VerificationToken;
import com.nutriLens.NutriLens.domain.port.in.auth.AuthResult;
import com.nutriLens.NutriLens.domain.port.in.auth.LoginUserUseCase;
import com.nutriLens.NutriLens.domain.port.in.auth.LoginWithGoogleUseCase;
import com.nutriLens.NutriLens.domain.port.in.auth.RegisterUserUseCase;
import com.nutriLens.NutriLens.domain.port.out.UserRepository;
import com.nutriLens.NutriLens.domain.port.out.VerificationTokenRepository;
import com.nutriLens.NutriLens.infrastructure.port.in.web.dto.request.*;
import com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response.AuthResponse;
import com.nutriLens.NutriLens.infrastructure.port.in.web.mapper.AuthDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticacion", description = "Endpoints para registro, login, verificacion y recuperacion de contrasena")
@SecurityRequirements()
public class AuthController {

    private final LoginUserUseCase loginUserUseCase;
    private final RegisterUserUseCase registerUserUseCase;
    private final LoginWithGoogleUseCase loginWithGoogleUseCase;
    private final AuthDtoMapper authDtoMapper;
    private final EmailService emailService;
    private final VerificationTokenRepository verificationTokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Operation(summary = "Iniciar sesion",
            description = "Autentica un usuario con email y contrasena, retornando tokens JWT de acceso y refresco")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login exitoso",
                    content = @Content(schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada invalidos"),
            @ApiResponse(responseCode = "401", description = "Credenciales incorrectas")
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResult result = loginUserUseCase.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(authDtoMapper.toDto(result));
    }

    @Operation(summary = "Registrar usuario",
            description = "Crea una nueva cuenta de usuario con perfil nutricional completo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro exitoso",
                    content = @Content(schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada invalidos"),
            @ApiResponse(responseCode = "409", description = "El email ya esta registrado")
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

    @Operation(summary = "Login con Google",
            description = "Autentica o registra un usuario mediante su cuenta de Google")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticacion exitosa",
                    content = @Content(schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "400", description = "Datos de Google invalidos")
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

    @Operation(summary = "Enviar codigo de verificacion",
            description = "Envia un codigo de 6 digitos al email para verificar la cuenta")
    @PostMapping("/send-verification")
    public ResponseEntity<Map<String, String>> sendVerification(@Valid @RequestBody SendVerificationRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Este email ya esta registrado"));
        }
        String code = generateCode();
        VerificationToken token = new VerificationToken(
                request.getEmail(), code, VerificationToken.TokenType.EMAIL_VERIFICATION);
        verificationTokenRepository.save(token);
        try {
            emailService.sendVerificationCode(request.getEmail(), code);
        } catch (Exception e) {
            log.warn("EMAIL NO ENVIADO (SMTP no disponible). Codigo para {}: [{}]", request.getEmail(), code);
        }
        return ResponseEntity.ok(Map.of("message", "Codigo de verificacion enviado a " + request.getEmail()));
    }

    @Operation(summary = "Verificar codigo de email",
            description = "Verifica el codigo de 6 digitos enviado al email")
    @PostMapping("/verify-email")
    public ResponseEntity<Map<String, Object>> verifyEmail(@Valid @RequestBody VerifyEmailRequest request) {
        var optToken = verificationTokenRepository.findByEmailAndCodeAndType(
                request.getEmail(), request.getCode(), VerificationToken.TokenType.EMAIL_VERIFICATION);
        if (optToken.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Codigo invalido o expirado"));
        }
        VerificationToken token = optToken.get();
        if (!token.isValid()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "El codigo ha expirado. Solicita uno nuevo."));
        }
        verificationTokenRepository.markAsUsed(token.getId());
        return ResponseEntity.ok(Map.of("verified", true, "message", "Email verificado exitosamente"));
    }

    @Operation(summary = "Solicitar restablecimiento de contrasena",
            description = "Envia un codigo de 6 digitos al email para restablecer la contrasena")
    @PostMapping("/forgot-password")
    public ResponseEntity<Map<String, String>> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "No existe una cuenta con este email"));
        }
        String code = generateCode();
        VerificationToken token = new VerificationToken(
                request.getEmail(), code, VerificationToken.TokenType.PASSWORD_RESET);
        verificationTokenRepository.save(token);
        try {
            emailService.sendPasswordResetCode(request.getEmail(), code);
        } catch (Exception e) {
            log.warn("EMAIL NO ENVIADO (SMTP no disponible). Codigo para {}: [{}]", request.getEmail(), code);
        }
        return ResponseEntity.ok(Map.of("message", "Codigo de recuperacion enviado a " + request.getEmail()));
    }

    @Operation(summary = "Restablecer contrasena",
            description = "Restablece la contrasena usando el codigo de verificacion")
    @PostMapping("/reset-password")
    public ResponseEntity<Map<String, Object>> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
        var optToken = verificationTokenRepository.findByEmailAndCodeAndType(
                request.getEmail(), request.getCode(), VerificationToken.TokenType.PASSWORD_RESET);
        if (optToken.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Codigo invalido o expirado"));
        }
        VerificationToken token = optToken.get();
        if (!token.isValid()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "El codigo ha expirado. Solicita uno nuevo."));
        }
        var optUser = userRepository.findByEmail(request.getEmail());
        if (optUser.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Usuario no encontrado"));
        }
        var optProvider = authProviderRepository.findByProviderEmail(request.getEmail());
        if (optProvider.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Metodo de autenticacion no encontrado"));
        }
        var authProvider = optProvider.get();
        if (!authProvider.isLocal()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "No se puede restablecer la contrasena de cuentas de Google"));
        }
        authProvider.setPasswordHash(passwordEncoder.encode(request.getNewPassword()));
        authProviderRepository.save(authProvider);
        verificationTokenRepository.markAsUsed(token.getId());
        return ResponseEntity.ok(Map.of("message", "Contrasena restablecida exitosamente"));
    }

    private String generateCode() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(999999));
    }

    private final com.nutriLens.NutriLens.domain.port.out.AuthProviderRepository authProviderRepository;
}
