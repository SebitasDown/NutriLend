package com.nutriLens.NutriLens.domain.port.in.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSession {
    private final Long userId;
    private final String email;
}
