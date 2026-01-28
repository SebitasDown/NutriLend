package com.nutriLens.NutriLens.domain.port.out;

import java.util.Optional;

public interface TokenValidator {
    boolean isValid(String token);

    Optional<Long> extractUserId(String token);

    Optional<String> extractEmail(String token);
}
