package com.nutriLens.NutriLens.infrastructure.port.out.persistence.entity;

import com.nutriLens.NutriLens.domain.model.AuthProviderType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "auth_providers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthProviderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    private AuthProviderType authProviderType;

    private String providerUserId;
    private String providerEmail;
    private String providerAvatarUrl;
    private String passwordHash;
}
