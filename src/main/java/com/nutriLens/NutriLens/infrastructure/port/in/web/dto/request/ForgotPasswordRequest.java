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
@Schema(description = "Datos para solicitar restablecimiento de contrasena")
public class ForgotPasswordRequest {
    @Schema(description = "Email del usuario registrado", example = "juan@ejemplo.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Email
    private String email;
}
