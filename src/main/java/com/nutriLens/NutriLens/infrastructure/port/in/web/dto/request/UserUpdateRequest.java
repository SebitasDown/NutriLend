package com.nutriLens.NutriLens.infrastructure.port.in.web.dto.request;

import com.nutriLens.NutriLens.domain.model.ActivityLevel;
import com.nutriLens.NutriLens.domain.model.Goal;
import com.nutriLens.NutriLens.domain.model.Preference;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {
    @NotBlank(message = "El nombre mostrado es obligatorio")
    private String displayName;

    private String avatarUrl;

    @NotNull(message = "El peso es obligatorio")
    @Min(value = 1, message = "El peso debe ser mayor a 0")
    private Float weight;

    @NotNull(message = "La altura es obligatoria")
    @Min(value = 1, message = "La altura debe ser mayor a 0")
    private Float height;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 1, message = "La edad debe ser mayor a 0")
    private Integer age;

    @NotNull(message = "La preferencia es obligatoria")
    private Preference preference;

    @NotNull(message = "El número de comidas es obligatorio")
    @Min(value = 1, message = "Debe haber al menos 1 comida")
    private Integer meals;

    @NotNull(message = "El objetivo es obligatorio")
    private Goal goal;

    @NotNull(message = "El nivel de actividad es obligatorio")
    private ActivityLevel activityLevel;
}
