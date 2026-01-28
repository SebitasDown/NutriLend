package com.nutriLens.NutriLens.infrastructure.port.out.persistence.mapper;

import com.nutriLens.NutriLens.domain.model.AuthProvider;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.entity.AuthProviderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface AuthProviderMapper {

    @Mapping(source = "user", target = "user")
    AuthProviderEntity toEntity(AuthProvider authProvider);

    @Mapping(source = "user", target = "user")
    AuthProvider toDomain(AuthProviderEntity entity);
}
