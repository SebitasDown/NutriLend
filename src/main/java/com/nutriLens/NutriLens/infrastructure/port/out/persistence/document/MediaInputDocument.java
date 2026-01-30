package com.nutriLens.NutriLens.infrastructure.port.out.persistence.document;

import com.nutriLens.NutriLens.domain.model.MediaType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaInputDocument {

    private MediaType type;
    private String cloudinaryUrl;

}
