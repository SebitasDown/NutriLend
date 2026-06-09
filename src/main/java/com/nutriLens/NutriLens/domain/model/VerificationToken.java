package com.nutriLens.NutriLens.domain.model;

import java.time.LocalDateTime;

public class VerificationToken {
    private Long id;
    private String email;
    private String code;
    private TokenType type;
    private LocalDateTime expiryDate;
    private boolean used;

    public VerificationToken() {}

    public VerificationToken(String email, String code, TokenType type) {
        this.email = email;
        this.code = code;
        this.type = type;
        this.expiryDate = LocalDateTime.now().plusMinutes(15);
        this.used = false;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public TokenType getType() { return type; }
    public void setType(TokenType type) { this.type = type; }
    public LocalDateTime getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDateTime expiryDate) { this.expiryDate = expiryDate; }
    public boolean isUsed() { return used; }
    public void setUsed(boolean used) { this.used = used; }

    public boolean isValid() {
        return !used && LocalDateTime.now().isBefore(expiryDate);
    }

    public enum TokenType {
        EMAIL_VERIFICATION,
        PASSWORD_RESET
    }
}
