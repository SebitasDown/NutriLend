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
@Schema(description = "Datos para verificar el codigo de email")
public class VerifyEmailRequest {
    @Schema(description = "Email del usuario", example = "juan@ejemplo.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Email
    private String email;

    @Schema(description = "Codigo de verificacion de 6 digitos", example = "123456", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(min = 6, max = 6)
    private String code;
}
