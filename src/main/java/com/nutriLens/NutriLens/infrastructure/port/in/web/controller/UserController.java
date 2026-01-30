package com.nutriLens.NutriLens.infrastructure.port.in.web.controller;

import com.nutriLens.NutriLens.domain.model.User;
import com.nutriLens.NutriLens.domain.port.in.auth.UserSession;
import com.nutriLens.NutriLens.domain.port.in.user.GetUserProfileUseCase;
import com.nutriLens.NutriLens.domain.port.in.user.UpdateProfilePhotoUseCase;
import com.nutriLens.NutriLens.domain.port.in.user.UpdateUserProfileUseCase;
import com.nutriLens.NutriLens.infrastructure.port.in.web.dto.request.UserUpdateRequest;
import com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response.UserResponseDto;
import com.nutriLens.NutriLens.infrastructure.port.in.web.mapper.UserDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = "Endpoints para gestión del perfil de usuario")
public class UserController {

    private final UpdateUserProfileUseCase updateUserProfileUseCase;
    private final GetUserProfileUseCase getUserProfileUseCase;
    private final UpdateProfilePhotoUseCase updateProfilePhotoUseCase;
    private final UserDtoMapper userDtoMapper;

    @Operation(
            summary = "Obtener perfil de usuario",
            description = "Retorna la información completa del perfil del usuario autenticado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil obtenido exitosamente",
                    content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "No autorizado - Token JWT inválido o expirado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/profile")
    public ResponseEntity<UserResponseDto> getProfile(
            @Parameter(hidden = true) @AuthenticationPrincipal UserSession session) {
        User user = getUserProfileUseCase.execute(session.getUserId());
        return ResponseEntity.ok(userDtoMapper.toDto(user));
    }

    @Operation(
            summary = "Actualizar perfil de usuario",
            description = "Actualiza la información del perfil del usuario incluyendo datos nutricionales"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "401", description = "No autorizado - Token JWT inválido o expirado")
    })
    @PutMapping("/profile")
    public ResponseEntity<UserResponseDto> updateProfile(
            @Parameter(hidden = true) @AuthenticationPrincipal UserSession session,
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

    @Operation(
            summary = "Subir foto de perfil",
            description = "Permite subir una imagen para el perfil del usuario. Formatos soportados: JPG, PNG, GIF"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Foto subida exitosamente",
                    content = @Content(schema = @Schema(example = "{\"avatarUrl\": \"https://cloudinary.com/...\"}"))),
            @ApiResponse(responseCode = "400", description = "Archivo vacío o formato no válido"),
            @ApiResponse(responseCode = "401", description = "No autorizado - Token JWT inválido o expirado")
    })
    @PostMapping(value = "/profile/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> updateProfilePhoto(
            @Parameter(hidden = true) @AuthenticationPrincipal UserSession session,
            @Parameter(
                    description = "Archivo de imagen (JPG, PNG, GIF)",
                    content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(type = "string", format = "binary"))
            )
            @RequestPart("file") MultipartFile file)
            throws IOException {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Por favor seleccione un archivo"));
        }

        String avatarUrl = updateProfilePhotoUseCase.uploadProfileImage(
                session.getUserId(),
                file.getBytes());
        return ResponseEntity.ok(Map.of("avatarUrl", avatarUrl));
    }
}
