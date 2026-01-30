package com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response;

import com.nutriLens.NutriLens.domain.model.ActivityLevel;
import com.nutriLens.NutriLens.domain.model.Goal;
import com.nutriLens.NutriLens.domain.model.Preference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Información del perfil del usuario")
public class UserResponseDto {
    @Schema(description = "ID único del usuario", example = "1")
    private Long id;

    @Schema(description = "Nombre para mostrar", example = "Juan Pérez")
    private String displayName;

    @Schema(description = "Email del usuario", example = "juan@ejemplo.com")
    private String email;

    @Schema(description = "URL de la foto de perfil", example = "https://cloudinary.com/avatar.jpg")
    private String avatarUrl;

    @Schema(description = "Peso en kilogramos", example = "70.5")
    private Float weight;

    @Schema(description = "Altura en centímetros", example = "175.0")
    private Float height;

    @Schema(description = "Edad en años", example = "28")
    private Integer age;

    @Schema(description = "Preferencia alimentaria")
    private Preference preference;

    @Schema(description = "Número de comidas diarias", example = "3")
    private Integer meals;

    @Schema(description = "Objetivo nutricional")
    private Goal goal;

    @Schema(description = "Nivel de actividad física")
    private ActivityLevel activityLevel;
}
