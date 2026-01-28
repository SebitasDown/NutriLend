package com.nutriLens.NutriLens.infrastructure.port.in.web.dto.request;

import com.nutriLens.NutriLens.domain.model.ActivityLevel;
import com.nutriLens.NutriLens.domain.model.Goal;
import com.nutriLens.NutriLens.domain.model.Preference;
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
public class RegisterRequest {
    @NotBlank(message = "El nombre es obligatorio")
    private String displayName;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del email no es válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    // Perfil completo (Sin avatarUrl en registro normal)
    @NotNull(message = "El peso es obligatorio")
    private Float weight;

    @NotNull(message = "La altura es obligatoria")
    private Float height;

    @NotNull(message = "La edad es obligatoria")
    private Integer age;

    @NotNull(message = "La preferencia es obligatoria")
    private com.nutriLens.NutriLens.domain.model.Preference preference;

    @NotNull(message = "El número de comidas es obligatorio")
    private Integer meals;

    @NotNull(message = "El objetivo es obligatorio")
    private com.nutriLens.NutriLens.domain.model.Goal goal;

    @NotNull(message = "El nivel de actividad es obligatorio")
    private com.nutriLens.NutriLens.domain.model.ActivityLevel activityLevel;
}
