package com.nutriLens.NutriLens.infrastructure.port.in.web.controller;

import com.nutriLens.NutriLens.domain.model.TypeFood;
import com.nutriLens.NutriLens.domain.port.in.recipe.GetRecipesForUserUseCase;
import com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response.RecipeResponseDto;
import com.nutriLens.NutriLens.infrastructure.port.in.web.mapper.RecipeDtoMapper;
import com.nutriLens.NutriLens.domain.port.in.auth.UserSession;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
@Tag(name = "Recetas", description = "Endpoints para obtener recetas personalizadas según el perfil del usuario")
public class RecipeController {

    private final GetRecipesForUserUseCase getRecipesForUserUseCase;
    private final RecipeDtoMapper recipeDtoMapper;

    @Operation(
            summary = "Obtener recetas personalizadas",
            description = "Retorna una lista de recetas filtradas por tipo de comida y adaptadas a las preferencias del usuario"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de recetas obtenida exitosamente",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = RecipeResponseDto.class)))),
            @ApiResponse(responseCode = "400", description = "Tipo de comida inválido"),
            @ApiResponse(responseCode = "401", description = "No autorizado - Token JWT inválido o expirado")
    })
    @GetMapping
    public List<RecipeResponseDto> getRecipesForUser(
            @Parameter(hidden = true) @AuthenticationPrincipal UserSession session,
            @Parameter(
                    description = "Tipo de comida para filtrar recetas",
                    required = true,
                    schema = @Schema(implementation = TypeFood.class)
            )
            @RequestParam TypeFood typeFood) {
        return getRecipesForUserUseCase.execute(session.getUserId(), typeFood)
                .stream()
                .map(recipeDtoMapper::toDto)
                .toList();
    }
}
