package com.nutriLens.NutriLens.infrastructure.port.out.persistence.entity;

import com.nutriLens.NutriLens.domain.model.VerificationToken;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "verification_tokens")
public class VerificationTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 6)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private VerificationToken.TokenType type;

    @Column(nullable = false)
    private LocalDateTime expiryDate;

    @Column(nullable = false)
    private boolean used;

    public VerificationTokenEntity() {}

    public VerificationTokenEntity(VerificationToken domain) {
        this.id = domain.getId();
        this.email = domain.getEmail();
        this.code = domain.getCode();
        this.type = domain.getType();
        this.expiryDate = domain.getExpiryDate();
        this.used = domain.isUsed();
    }

    public VerificationToken toDomain() {
        VerificationToken token = new VerificationToken();
        token.setId(this.id);
        token.setEmail(this.email);
        token.setCode(this.code);
        token.setType(this.type);
        token.setExpiryDate(this.expiryDate);
        token.setUsed(this.used);
        return token;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public VerificationToken.TokenType getType() { return type; }
    public void setType(VerificationToken.TokenType type) { this.type = type; }
    public LocalDateTime getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDateTime expiryDate) { this.expiryDate = expiryDate; }
    public boolean isUsed() { return used; }
    public void setUsed(boolean used) { this.used = used; }
}
