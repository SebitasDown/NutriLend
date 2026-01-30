package com.nutriLens.NutriLens.infrastructure.port.in.web.controller;

import com.nutriLens.NutriLens.domain.model.DailyNutrition;
import com.nutriLens.NutriLens.domain.model.MealAnalysis;
import com.nutriLens.NutriLens.domain.model.MediaType;
import com.nutriLens.NutriLens.domain.model.MealType;
import com.nutriLens.NutriLens.domain.port.in.NutritionProfile.GetDailyNutritionUseCase;
import com.nutriLens.NutriLens.domain.port.in.analyzeIa.AnalyzeMealUseCase;
import com.nutriLens.NutriLens.domain.port.in.analyzeIa.GetMealHistoryUseCase;
import com.nutriLens.NutriLens.domain.port.in.auth.UserSession;
import com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response.DailyNutritionResponseDto;
import com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response.MealAnalysisResponseDto;
import com.nutriLens.NutriLens.infrastructure.port.in.web.mapper.MealAnalysisDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/meals")
@RequiredArgsConstructor
@Tag(name = "Meal Analysis", description = "Endpoints para análisis de comidas con IA")
public class MealController {

        private final AnalyzeMealUseCase analyzeMealUseCase;
        private final GetMealHistoryUseCase getMealHistoryUseCase;
        private final GetDailyNutritionUseCase getDailyNutritionUseCase;
        private final MealAnalysisDtoMapper mealAnalysisDtoMapper;

        @Operation(
                summary = "Analizar comida",
                description = "Sube una imagen o audio de una comida para obtener información nutricional mediante IA. " +
                        "La IA detectará los alimentos y calculará calorías, proteínas, carbohidratos y grasas."
        )
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Análisis completado exitosamente",
                        content = @Content(schema = @Schema(implementation = MealAnalysisResponseDto.class))),
                @ApiResponse(responseCode = "400", description = "Archivo vacío o formato no soportado"),
                @ApiResponse(responseCode = "401", description = "No autorizado - Token JWT inválido o expirado"),
                @ApiResponse(responseCode = "500", description = "Error al procesar el archivo con IA")
        })
        @PostMapping(value = "/analyze", consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<?> analyzeMeal(
                        @Parameter(hidden = true) @AuthenticationPrincipal UserSession session,
                        @Parameter(description = "Archivo de imagen o audio de la comida", content = @Content(mediaType = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE, schema = @Schema(type = "string", format = "binary"))) @RequestPart("file") MultipartFile file,
                        @Parameter(description = "Tipo de media: IMAGE o AUDIO") @RequestParam("type") MediaType type,
                        @Parameter(description = "Tipo de comida: BREAKFAST, LUNCH, DINNER, SNACK") @RequestParam("mealType") MealType mealType)
                        throws IOException {

                if (file.isEmpty()) {
                        return ResponseEntity.badRequest()
                                        .body(Map.of("error", "Por favor seleccione un archivo"));
                }

                MealAnalysis analysis = analyzeMealUseCase.analyze(
                                session.getUserId(),
                                file.getBytes(),
                                type,
                                mealType);

                MealAnalysisResponseDto responseDto = mealAnalysisDtoMapper.toDto(analysis);

                return ResponseEntity.ok(responseDto);
        }

        @Operation(
                summary = "Obtener historial de comidas",
                description = "Retorna el historial completo de análisis de comidas del usuario autenticado, ordenado por fecha"
        )
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Historial obtenido exitosamente",
                        content = @Content(array = @ArraySchema(schema = @Schema(implementation = MealAnalysisResponseDto.class)))),
                @ApiResponse(responseCode = "401", description = "No autorizado - Token JWT inválido o expirado")
        })
        @GetMapping("/history")
        public ResponseEntity<List<MealAnalysisResponseDto>> getMealHistory(
                        @Parameter(hidden = true) @AuthenticationPrincipal UserSession session) {
                List<MealAnalysis> history = getMealHistoryUseCase.execute(session.getUserId());
                List<MealAnalysisResponseDto> historyDto = history.stream()
                                .map(mealAnalysisDtoMapper::toDto)
                                .toList();
                return ResponseEntity.ok(historyDto);
        }

        @Operation(
                summary = "Obtener resumen nutricional diario",
                description = "Retorna el resumen de consumo nutricional del día especificado, incluyendo progreso hacia la meta calórica"
        )
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Resumen obtenido exitosamente",
                        content = @Content(schema = @Schema(implementation = DailyNutritionResponseDto.class))),
                @ApiResponse(responseCode = "401", description = "No autorizado - Token JWT inválido o expirado")
        })
        @GetMapping("/summary")
        public ResponseEntity<DailyNutritionResponseDto> getDailySummary(
                        @Parameter(hidden = true) @AuthenticationPrincipal UserSession session,
                        @Parameter(description = "Fecha a consultar (yyyy-MM-dd), por defecto hoy") @RequestParam(value = "date", required = false) LocalDate date,
                        @Parameter(description = "Offset de zona horaria (ej: -05:00)") @RequestParam(value = "timezoneOffset", defaultValue = "Z") String timezoneOffset) {

                ZoneOffset offset = ZoneOffset.of(timezoneOffset);
                LocalDate targetDate = (date != null) ? date : LocalDate.now(offset);
                DailyNutrition dailyNutrition = getDailyNutritionUseCase.getDailyNutrition(session.getUserId(),
                                targetDate, offset);

                DailyNutritionResponseDto response = new DailyNutritionResponseDto(
                                dailyNutrition.getTotalCalories(),
                                dailyNutrition.getTotalProtein(),
                                dailyNutrition.getTotalCarbs(),
                                dailyNutrition.getTotalFats(),
                                dailyNutrition.getCalorieGoal(),
                                dailyNutrition.getCalorieProgressPercentage());

                return ResponseEntity.ok(response);
        }
}
