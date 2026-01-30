package com.nutriLens.NutriLens.infrastructure.port.out.persistence.adapter;

import com.nutriLens.NutriLens.domain.model.Meal;
import com.nutriLens.NutriLens.domain.port.out.MealRepository;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.document.MealDocument;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.mapper.MealMapper;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.repository.MongoMealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MealAdapter implements MealRepository {

    private final MongoMealRepository repository;
    private final MealMapper mapper;

    @Override
    public Meal save(Meal meal) {
        MealDocument doc = mapper.toDocument(meal);
        MealDocument saved = repository.save(doc);
        return mapper.toDomain(saved);
    }

    @Override
    public List<Meal> findByUserAndDate(Long userId, LocalDate date, ZoneOffset offset) {
        var start = date.atStartOfDay().toInstant(offset);
        var end = date.atTime(LocalTime.MAX).toInstant(offset);

        return repository.findByUserIdAndEatenAtBetween(userId, start, end)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
