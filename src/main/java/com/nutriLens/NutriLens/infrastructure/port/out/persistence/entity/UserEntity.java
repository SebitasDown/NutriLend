package com.nutriLens.NutriLens.infrastructure.port.out.persistence.entity;

import com.nutriLens.NutriLens.domain.model.ActivityLevel;
import com.nutriLens.NutriLens.domain.model.Goal;
import com.nutriLens.NutriLens.domain.model.Preference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    private String displayName;
    private String avatarUrl;

    // Profile Data
    private Float weight;
    private Float height;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private Preference preference;

    private Integer meals;

    @Enumerated(EnumType.STRING)
    private Goal goal;

    @Enumerated(EnumType.STRING)
    private ActivityLevel activityLevel;

    @Column(name = "is_deleted")
    private boolean isDelete;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
