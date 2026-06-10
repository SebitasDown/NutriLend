package com.nutriLens.NutriLens.domain.port.in.analyzeIa;

import com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response.MealBreakdownResponseDto;

import java.time.LocalDate;
import java.time.ZoneOffset;

public interface GetMealBreakdownUseCase {
    MealBreakdownResponseDto execute(Long userId, LocalDate date, ZoneOffset offset);
}
