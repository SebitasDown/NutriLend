package com.nutriLens.NutriLens.application.service.auth;

import com.nutriLens.NutriLens.application.exception.InvalidCredential;

import com.nutriLens.NutriLens.domain.model.AuthProvider;
import com.nutriLens.NutriLens.domain.port.in.auth.AuthResult;
import com.nutriLens.NutriLens.domain.port.in.auth.LoginUserUseCase;
import com.nutriLens.NutriLens.domain.port.out.AuthProviderRepository;
import com.nutriLens.NutriLens.domain.port.out.PasswordHasher;
import com.nutriLens.NutriLens.domain.port.out.TokenProvider;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCaseImpl implements LoginUserUseCase {

    private final AuthProviderRepository authProviderRepository;
    private final PasswordHasher passwordHasher;
    private final TokenProvider tokenProvider;

    public LoginUseCaseImpl(AuthProviderRepository authProviderRepository, PasswordHasher passwordHasher, TokenProvider tokenProvider) {
        this.authProviderRepository = authProviderRepository;
        this.passwordHasher = passwordHasher;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public AuthResult login(String email, String password) {
       AuthProvider authProvider = authProviderRepository.findByProviderEmail(email)
                .orElseThrow(() -> new InvalidCredential("Acceso Invalido"));

        // feature: Crear metodo para eliminar usuario
        if (authProvider.getUser().isDelete()){
            throw new InvalidCredential("No Valido");
        }

        if (!passwordHasher.matches(password, authProvider.getPasswordHash())) {
             throw new InvalidCredential("Acceso Invalido");
        }

        String accessToken = tokenProvider.generateAccessToken(authProvider.getUser());
        String refreshToken = tokenProvider.generateRefreshToken(authProvider.getUser());
        return new AuthResult(accessToken, refreshToken);
    }
}
