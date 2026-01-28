package com.nutriLens.NutriLens.domain.port.out;

public interface PasswordHasher {
    String hash(String password);
    boolean matches(String password, String hashedPasword);
}
