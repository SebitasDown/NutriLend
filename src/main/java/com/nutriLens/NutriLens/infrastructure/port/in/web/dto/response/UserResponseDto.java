package com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response;

import com.nutriLens.NutriLens.domain.model.ActivityLevel;
import com.nutriLens.NutriLens.domain.model.Goal;
import com.nutriLens.NutriLens.domain.model.Preference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String displayName;
    private String email;
    private String avatarUrl;
    private Float weight;
    private Float height;
    private Integer age;
    private Preference preference;
    private Integer meals;
    private Goal goal;
    private ActivityLevel activityLevel;
}
