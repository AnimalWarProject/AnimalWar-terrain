package com.example.animalwarterrain.domain.dto;

import com.example.animalwarterrain.domain.entity.LandForm;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;


public record TerrainResponseDto(
 UUID userUUID,
 LandForm landForm

        ) {

}
