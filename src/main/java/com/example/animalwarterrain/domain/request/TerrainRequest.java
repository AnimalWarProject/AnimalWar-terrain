package com.example.animalwarterrain.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TerrainRequest {
    private UUID userUUID;
    private int gold;
}
