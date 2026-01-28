package com.nutriLens.NutriLens.infrastructure.port.in.web.mapper;

import com.nutriLens.NutriLens.domain.port.in.auth.AuthResult;
import com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response.AuthResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthDtoMapper {
    AuthResponse toDto(AuthResult authResult);
}
