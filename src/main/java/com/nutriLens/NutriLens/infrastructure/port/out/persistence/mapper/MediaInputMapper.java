package com.nutriLens.NutriLens.infrastructure.port.out.persistence.mapper;

import com.nutriLens.NutriLens.domain.model.MediaInput;
import com.nutriLens.NutriLens.infrastructure.port.out.persistence.document.MediaInputDocument;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MediaInputMapper {

    MediaInputDocument toDocument(MediaInput mediaInput);

    MediaInput toDomain(MediaInputDocument document);
}
