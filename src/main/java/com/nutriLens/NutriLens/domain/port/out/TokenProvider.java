package com.nutriLens.NutriLens.domain.port.out;

import com.nutriLens.NutriLens.domain.model.User;

// Generacion de tocken para infrastructure
public interface TokenProvider {
    String generateAccessToken(User user);

    String generateRefreshToken(User user);
}
