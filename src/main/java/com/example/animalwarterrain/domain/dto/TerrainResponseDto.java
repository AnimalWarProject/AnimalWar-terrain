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
        private UUID userUUID;
        private LandForm dominantlandForm;
        private int sea;
        private int land;
        private int mountain;
}
