package com.nutriLens.NutriLens.application.service.auth;

import com.nutriLens.NutriLens.application.exception.Alredyexisting;
import com.nutriLens.NutriLens.domain.model.AuthProvider;
import com.nutriLens.NutriLens.domain.model.AuthProviderType;
import com.nutriLens.NutriLens.domain.model.User;
import com.nutriLens.NutriLens.domain.port.in.auth.AuthResult;
import com.nutriLens.NutriLens.domain.port.in.auth.RegisterUserUseCase;
import com.nutriLens.NutriLens.domain.port.out.AuthProviderRepository;
import com.nutriLens.NutriLens.domain.port.out.PasswordHasher;
import com.nutriLens.NutriLens.domain.port.out.TokenProvider;
import com.nutriLens.NutriLens.domain.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class RegisterUserUseCaseImpl implements RegisterUserUseCase {

    private final UserRepository userRepository;
    private final AuthProviderRepository authProviderRepository;
    private final PasswordHasher passwordHasher;
    private final TokenProvider tokenProvider;

    public RegisterUserUseCaseImpl(UserRepository userRepository, AuthProviderRepository authProviderRepository,
            PasswordHasher passwordHasher, TokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.authProviderRepository = authProviderRepository;
        this.passwordHasher = passwordHasher;
        this.tokenProvider = tokenProvider;
    }

    @Override
    @Transactional
    public AuthResult register(String displayName, String email, String password, Float weight,
            Float height,
            Integer age, com.nutriLens.NutriLens.domain.model.Preference preference, Integer meals,
            com.nutriLens.NutriLens.domain.model.Goal goal,
            com.nutriLens.NutriLens.domain.model.ActivityLevel activityLevel) {

        userRepository.findByEmail(email)
                .ifPresent(user -> {
                    throw new Alredyexisting("Este usuario ya esta registrado");
                });

        System.out.println("DEBUG: Registering user " + email);
        String passwordHash = passwordHasher.hash(password);

        System.out.println("DEBUG: Creating User object");
        User user = new User();
        user.setDisplayName(displayName);
        user.setEmail(email);
        user.setWeight(weight);
        user.setHeight(height);
        user.setAge(age);
        user.setPreference(preference);
        user.setMeals(meals);
        user.setGoal(goal);
        user.setActivityLevel(activityLevel);

        User savedUser = userRepository.save(user);

        AuthProvider authProvider = new AuthProvider(savedUser, AuthProviderType.LOCAL, email);
        authProvider.setProviderEmail(email);
        authProvider.setPasswordHash(passwordHash);
        authProviderRepository.save(authProvider);

        String accessToken = tokenProvider.generateAccessToken(savedUser);
        String refreshToken = tokenProvider.generateRefreshToken(savedUser);

        return new AuthResult(accessToken, refreshToken);
    }
}
