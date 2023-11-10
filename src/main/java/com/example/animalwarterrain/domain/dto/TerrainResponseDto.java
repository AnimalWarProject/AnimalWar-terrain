package com.example.animalwarterrain.domain.dto;

import com.example.animalwarterrain.domain.entity.LandForm;

import java.util.UUID;

public record TerrainResponseDto(
        UUID userUUID,
        LandForm dominantLandForm,
        int sea,
        int land,
        int mountain
) {
}
