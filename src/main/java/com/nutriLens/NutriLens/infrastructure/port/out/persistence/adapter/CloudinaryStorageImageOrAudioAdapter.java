package com.nutriLens.NutriLens.infrastructure.port.out.persistence.adapter;

import com.cloudinary.Cloudinary;
import com.nutriLens.NutriLens.domain.model.MediaType;
import com.nutriLens.NutriLens.domain.port.out.MediaStoragePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class CloudinaryStorageImageOrAudioAdapter implements MediaStoragePort {

    private final Cloudinary cloudinary;

    @Override
    public String upload(byte[] file, MediaType type) {
        try {
            String resourceType = switch (type) {
                case IMAGE -> "image";
                case AUDIO -> "video"; // Cloudinary usa "video" para audio
                case TEXT -> throw new UnsupportedOperationException("No se puede subir texto a Cloudinary");
            };

            String folder = switch (type) {
                case IMAGE -> "meal_images";
                case AUDIO -> "meal_audio";
                case TEXT -> throw new UnsupportedOperationException("No se puede subir texto a Cloudinary");
            };

            Map<?, ?> result = cloudinary.uploader().upload(
                    file,
                    Map.of(
                            "folder", folder,
                            "resource_type", resourceType));

            return result.get("secure_url").toString();
        } catch (Exception e) {
            System.err.println("ERROR UPLOADING TO CLOUDINARY: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error subiendo " + type + " a Cloudinary: " + e.getMessage(), e);
        }
    }
}
