package com.nutriLens.NutriLens.domain.port.out;

import com.nutriLens.NutriLens.domain.model.Meal;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

public interface MealRepository {

    Meal save(Meal meal);

    List<Meal> findByUserAndDate(Long userId, LocalDate date, ZoneOffset offset);
}
