package com.example.animalwarterrain.domain.dto;

import java.util.List;
import java.util.UUID;

public record PlaceRequest(
        UUID uuid,
        List<TileInfo> tileInfos

) {
}
