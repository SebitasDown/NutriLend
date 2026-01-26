package com.nutriLens.NutriLens.domain.model;

// Autenticacion si se inicia en google u otro / Tambien local
public class AuthProvider {

    // id del usuario y de provider
    private Long id;
    private User user;

    // identidad del perfil externo
    private AuthProviderType authProviderType;
    private String providerUserId;

    // Imagen y email del usuario
    private String providerAvatarUrl;
    private String providerEmail;

    // Contraseña para usuario Local
    private String passwordHash;

    public AuthProvider(User user, AuthProviderType authProviderType, String providerUserId) {
        this.user = user;
        this.authProviderType = authProviderType;
        this.providerUserId = providerUserId;
    }

    public String getProviderEmail() {
        return providerEmail;
    }

    public void setProviderEmail(String providerEmail) {
        this.providerEmail = providerEmail;
    }

    public String getProviderUserId() {
        return providerUserId;
    }

    public void setProviderAvatarUrl(String providerAvatarUrl) {
        this.providerAvatarUrl = providerAvatarUrl;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
