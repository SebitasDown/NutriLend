package com.nutriLens.NutriLens.infrastructure.port.in.web.dto.request;

import com.nutriLens.NutriLens.domain.model.ActivityLevel;
import com.nutriLens.NutriLens.domain.model.Goal;
import com.nutriLens.NutriLens.domain.model.Preference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos para actualizar el perfil del usuario")
public class UserUpdateRequest {
    @Schema(description = "Nombre para mostrar del usuario", example = "Juan Pérez", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "El nombre mostrado es obligatorio")
    private String displayName;

    @Schema(description = "URL de la foto de perfil", example = "https://cloudinary.com/avatar.jpg")
    private String avatarUrl;

    @Schema(description = "Peso del usuario en kilogramos", example = "70.5", minimum = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "El peso es obligatorio")
    @Min(value = 1, message = "El peso debe ser mayor a 0")
    private Float weight;

    @Schema(description = "Altura del usuario en centímetros", example = "175.0", minimum = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "La altura es obligatoria")
    @Min(value = 1, message = "La altura debe ser mayor a 0")
    private Float height;

    @Schema(description = "Edad del usuario en años", example = "28", minimum = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "La edad es obligatoria")
    @Min(value = 1, message = "La edad debe ser mayor a 0")
    private Integer age;

    @Schema(description = "Preferencia alimentaria del usuario", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "La preferencia es obligatoria")
    private Preference preference;

    @Schema(description = "Número de comidas diarias", example = "3", minimum = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "El número de comidas es obligatorio")
    @Min(value = 1, message = "Debe haber al menos 1 comida")
    private Integer meals;

    @Schema(description = "Objetivo nutricional del usuario", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "El objetivo es obligatorio")
    private Goal goal;

    @Schema(description = "Nivel de actividad física del usuario", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "El nivel de actividad es obligatorio")
    private ActivityLevel activityLevel;
}
