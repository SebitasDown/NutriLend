package com.nutriLens.NutriLens.infrastructure.security;

import com.nutriLens.NutriLens.domain.port.out.PasswordHasher;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


// hasheo de contraseña y comparador de contraseña
@Component
@RequiredArgsConstructor
public class BCryptPasswordHasher implements PasswordHasher {

    private final BCryptPasswordEncoder encoder;

    @Override
    public String hash(String password) {
        return encoder.encode(password);
    }

    @Override
    public boolean matches(String password, String hashedPasword) {
        return encoder.matches(password, hashedPasword);
    }
}
