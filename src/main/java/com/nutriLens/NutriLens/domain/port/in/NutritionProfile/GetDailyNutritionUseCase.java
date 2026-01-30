package com.nutriLens.NutriLens.domain.port.in.NutritionProfile;

import com.nutriLens.NutriLens.domain.model.DailyNutrition;
import java.time.LocalDate;
import java.time.ZoneOffset;

public interface GetDailyNutritionUseCase {

    DailyNutrition getDailyNutrition(Long userId, LocalDate date, ZoneOffset offset);
}
