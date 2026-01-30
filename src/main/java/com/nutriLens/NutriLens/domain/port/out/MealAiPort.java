package com.nutriLens.NutriLens.domain.port.out;

import com.nutriLens.NutriLens.domain.model.MediaType;
import com.nutriLens.NutriLens.domain.model.NutritionProfile;

// para mandarle la informacion a la ia y construir el perfil nutricional
public interface MealAiPort {

    NutritionProfile analyze (byte[] fileByte, MediaType type);
}
