package com.nutriLens.NutriLens.infrastructure.port.in.web.controller;

import com.nutriLens.NutriLens.domain.model.TypeFood;
import com.nutriLens.NutriLens.domain.port.in.recipe.GetRecipesForUserUseCase;
import com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response.RecipeResponseDto;
import com.nutriLens.NutriLens.infrastructure.port.in.web.mapper.RecipeDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final GetRecipesForUserUseCase getRecipesForUserUseCase;
    private final RecipeDtoMapper recipeDtoMapper;

    @GetMapping
    public List<RecipeResponseDto> getRecipesForUser (
            @RequestHeader("X-USER-ID") Long userId,
            @RequestParam TypeFood typeFood
            ){
        return getRecipesForUserUseCase.execute(userId, typeFood)
                .stream()
                .map(recipeDtoMapper::toDto)
                .toList();
    }
}
