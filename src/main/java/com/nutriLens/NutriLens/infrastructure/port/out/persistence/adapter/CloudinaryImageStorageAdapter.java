package com.nutriLens.NutriLens.infrastructure.port.out.persistence.adapter;

import com.cloudinary.Cloudinary;
import com.nutriLens.NutriLens.domain.port.out.ImageStoragePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class CloudinaryImageStorageAdapter implements ImageStoragePort {

    private final Cloudinary cloudinary;

    @Override
    public String uploadProfileImage(byte[] imagesBytes) {
        try{
            Map<?, ?> result = cloudinary.uploader().upload(
                    imagesBytes,
                    Map.of(
                            "folder", "profile_pictures",
                            "resource_type", "image"
                    )
            );

            return result.get("secure_url").toString();
        }catch (Exception e){
            throw new RuntimeException("Error subiendo imagen a claudinary");
        }
    }
}
