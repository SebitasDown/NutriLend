package com.nutriLens.NutriLens.infrastructure.port.in.web.mapper;

import com.nutriLens.NutriLens.domain.model.User;
import com.nutriLens.NutriLens.infrastructure.port.in.web.dto.response.UserResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    UserResponseDto toDto(User user);
}
