package com.nutriLens.NutriLens.infrastructure.port.out.persistence.mapper;

import com.nutriLens.NutriLens.domain.model.User;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toEntity(User user);

    User toDomain(UserEntity entity);
}
