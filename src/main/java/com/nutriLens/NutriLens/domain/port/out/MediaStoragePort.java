package com.nutriLens.NutriLens.domain.port.out;

import com.nutriLens.NutriLens.domain.model.MediaType;

// Esto es para subir la imagen a cloudinary
public interface MediaStoragePort {
    String upload(byte[] file, MediaType type);
}
