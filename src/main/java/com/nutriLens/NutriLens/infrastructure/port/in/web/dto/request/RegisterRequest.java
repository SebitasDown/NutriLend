package com.nutriLens.NutriLens.infrastructure.port.in.web.dto.request;

import com.nutriLens.NutriLens.domain.model.ActivityLevel;
import com.nutriLens.NutriLens.domain.model.Goal;
import com.nutriLens.NutriLens.domain.model.Preference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos para registrar un nuevo usuario con su perfil nutricional")
public class RegisterRequest {
    @Schema(description = "Nombre para mostrar del usuario", example = "Juan Pérez", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "El nombre es obligatorio")
    private String displayName;

    @Schema(description = "Email del usuario", example = "juan@ejemplo.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del email no es válido")
    private String email;

    @Schema(description = "Contraseña (mínimo 6 caracteres)", example = "miContraseña123", minLength = 6, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    @Schema(description = "Peso del usuario en kilogramos", example = "70.5", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "El peso es obligatorio")
    private Float weight;

    @Schema(description = "Altura del usuario en centímetros", example = "175.0", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "La altura es obligatoria")
    private Float height;

    @Schema(description = "Edad del usuario en años", example = "28", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "La edad es obligatoria")
    private Integer age;

    @Schema(description = "Preferencia alimentaria del usuario", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "La preferencia es obligatoria")
    private Preference preference;

    @Schema(description = "Número de comidas diarias", example = "3", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "El número de comidas es obligatorio")
    private Integer meals;

    @Schema(description = "Objetivo nutricional del usuario", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "El objetivo es obligatorio")
    private Goal goal;

    @Schema(description = "Nivel de actividad física del usuario", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "El nivel de actividad es obligatorio")
    private ActivityLevel activityLevel;
}
