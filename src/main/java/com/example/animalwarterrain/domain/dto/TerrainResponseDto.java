package com.example.animalwarterrain.domain.dto;

import com.example.animalwarterrain.domain.entity.LandForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TerrainResponseDto {
        UUID userUUID;
        LandForm dominantLandForm;
        int sea;
        int land;
        int mountain;

}
