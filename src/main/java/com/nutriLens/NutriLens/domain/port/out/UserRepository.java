package com.nutriLens.NutriLens.domain.port.out;

import com.nutriLens.NutriLens.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    User save (User user);
    Optional<User> findById(Long userId);
    Optional<User> findByEmail(String email);
}
