package com.example.animalwarterrain.domain.dto;

import java.util.List;
import java.util.UUID;

public record BatchRequest(
        UUID uuid,
        List<TileInfo> tileInfos

) {
}
