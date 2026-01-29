package com.nutriLens.NutriLens.infrastructure.port.in.web.controller;

import com.nutriLens.NutriLens.domain.model.MealAnalysis;
import com.nutriLens.NutriLens.domain.model.MediaType;
import com.nutriLens.NutriLens.domain.port.in.analyzeIa.AnalyzeMealUseCase;
import com.nutriLens.NutriLens.domain.port.in.analyzeIa.GetMealHistoryUseCase;
import com.nutriLens.NutriLens.domain.port.in.auth.UserSession;
import com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response.MealAnalysisResponseDto;
import com.nutriLens.NutriLens.infrastructure.port.in.web.mapper.MealAnalysisDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/meals")
@RequiredArgsConstructor
@Tag(name = "Meal Analysis", description = "Endpoints para análisis de comidas con IA")
public class MealController {

    private final AnalyzeMealUseCase analyzeMealUseCase;
    private final GetMealHistoryUseCase getMealHistoryUseCase;
    private final MealAnalysisDtoMapper mealAnalysisDtoMapper;

    @Operation(
            summary = "Analizar comida",
            description = "Sube una imagen o audio de una comida para obtener información nutricional mediante IA"
    )
    @PostMapping(value = "/analyze", consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> analyzeMeal(
            @Parameter(hidden = true) @AuthenticationPrincipal UserSession session,
            @Parameter(
                    description = "Archivo de imagen o audio de la comida",
                    content = @Content(
                            mediaType = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(type = "string", format = "binary")
                    )
            )
            @RequestPart("file") MultipartFile file,
            @Parameter(description = "Tipo de media: IMAGE o AUDIO")
            @RequestParam("type") MediaType type
    ) throws IOException {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Por favor seleccione un archivo"));
        }

        MealAnalysis analysis = analyzeMealUseCase.analyze(
                session.getUserId(),
                file.getBytes(),
                type
        );

        MealAnalysisResponseDto responseDto = mealAnalysisDtoMapper.toDto(analysis);

        return ResponseEntity.ok(responseDto);
    }

    @Operation(
            summary = "Obtener historial de comidas",
            description = "Retorna el historial de análisis de comidas del usuario autenticado"
    )
    @GetMapping("/history")
    public ResponseEntity<List<MealAnalysisResponseDto>> getMealHistory(
            @Parameter(hidden = true) @AuthenticationPrincipal UserSession session
    ) {
        List<MealAnalysis> history = getMealHistoryUseCase.execute(session.getUserId());
        List<MealAnalysisResponseDto> historyDto = history.stream()
                .map(mealAnalysisDtoMapper::toDto)
                .toList();
        return ResponseEntity.ok(historyDto);
    }
}
