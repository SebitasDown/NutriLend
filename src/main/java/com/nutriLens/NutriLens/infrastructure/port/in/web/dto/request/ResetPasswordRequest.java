package com.nutriLens.NutriLens.infrastructure.port.in.web.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos para restablecer la contrasena con codigo")
public class ResetPasswordRequest {
    @Schema(description = "Email del usuario", example = "juan@ejemplo.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Email
    private String email;

    @Schema(description = "Codigo de restablecimiento de 6 digitos", example = "654321", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(min = 6, max = 6)
    private String code;

    @Schema(description = "Nueva contrasena (minimo 6 caracteres)", example = "nuevaContra123", minLength = 6, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(min = 6)
    private String newPassword;
}
