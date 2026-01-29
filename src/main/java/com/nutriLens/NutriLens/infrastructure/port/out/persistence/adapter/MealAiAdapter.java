package com.nutriLens.NutriLens.infrastructure.port.out.persistence.adapter;

import com.nutriLens.NutriLens.domain.model.MediaType;
import com.nutriLens.NutriLens.domain.model.NutritionProfile;
import com.nutriLens.NutriLens.domain.port.out.MealAiPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MealAiAdapter implements MealAiPort {

    // TODO: Implementar integración con servicio de IA para análisis de comidas
    // Este es un stub temporal para que la aplicación pueda iniciar

    @Override
    public NutritionProfile analyze(byte[] fileByte, MediaType type) {
        log.warn("MealAiAdapter.analyze() - Implementación stub. Tipo: {}, Tamaño: {} bytes", 
                type, fileByte.length);
        
        // Retornar datos de ejemplo temporal
        return new NutritionProfile(500, 25.0f, 60.0f, 15.0f);
    }
}
