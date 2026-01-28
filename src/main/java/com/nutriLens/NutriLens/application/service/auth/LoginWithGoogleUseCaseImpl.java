package com.nutriLens.NutriLens.application.service.auth;

import com.nutriLens.NutriLens.application.exception.InvalidCredential;
import com.nutriLens.NutriLens.domain.model.AuthProvider;
import com.nutriLens.NutriLens.domain.model.AuthProviderType;
import com.nutriLens.NutriLens.domain.model.User;
import com.nutriLens.NutriLens.domain.port.in.auth.AuthResult;
import com.nutriLens.NutriLens.domain.port.in.auth.LoginWithGoogleUseCase;
import com.nutriLens.NutriLens.domain.port.out.AuthProviderRepository;
import com.nutriLens.NutriLens.domain.port.out.TokenProvider;
import com.nutriLens.NutriLens.domain.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class LoginWithGoogleUseCaseImpl implements LoginWithGoogleUseCase {

    private final AuthProviderRepository authProviderRepository;
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    public LoginWithGoogleUseCaseImpl(AuthProviderRepository authProviderRepository, UserRepository userRepository,
            TokenProvider tokenProvider) {
        this.authProviderRepository = authProviderRepository;
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
    }

    @Override
    @Transactional
    public AuthResult loginWithGoogle(String googleSub, String email, String name, String avatarUrl) {

        AuthProvider authProvider = authProviderRepository
                .findByProviderUserIdAndType(googleSub, AuthProviderType.GOOGLE)
                .orElseGet(() -> {
                    User user = new User();
                    user.setEmail(email);
                    user.setDisplayName(name);
                    User savedUser = userRepository.save(user);

                    AuthProvider newProvider = new AuthProvider(savedUser, AuthProviderType.GOOGLE, googleSub);
                    newProvider.setProviderEmail(email);
                    newProvider.setProviderAvatarUrl(avatarUrl);
                    return authProviderRepository.save(newProvider);
                });

        User user = authProvider.getUser();

        if (user.isDelete()) {
            throw new InvalidCredential("Usuario deshabilitado");
        }

        String accessToken = tokenProvider.generateAccessToken(user);
        String refreshToken = tokenProvider.generateRefreshToken(user);

        return new AuthResult(accessToken, refreshToken);
    }
}
