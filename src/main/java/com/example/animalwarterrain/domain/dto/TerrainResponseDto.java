package com.example.animalwarterrain.domain.dto;

import com.example.animalwarterrain.domain.entity.LandForm;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class TerrainResponseDto {
    private UUID userUUID;
    private int land;
    private int sea;
    private int mountain;
    private LandForm landForm;
    private int goldDeduction;
}
