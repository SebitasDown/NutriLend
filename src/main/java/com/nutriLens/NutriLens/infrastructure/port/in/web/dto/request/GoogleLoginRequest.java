package com.nutriLens.NutriLens.infrastructure.port.in.web.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos para autenticación con Google OAuth")
public class GoogleLoginRequest {
    @Schema(description = "ID único de Google (subject claim del token)", example = "123456789012345678901", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "El ID de Google (sub) es obligatorio")
    private String googleSub;

    @Schema(description = "Email de la cuenta de Google", example = "usuario@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del email no es válido")
    private String email;

    @Schema(description = "Nombre del usuario en Google", example = "Juan Pérez")
    private String name;

    @Schema(description = "URL del avatar de Google", example = "https://lh3.googleusercontent.com/a/...")
    private String avatarUrl;
}
