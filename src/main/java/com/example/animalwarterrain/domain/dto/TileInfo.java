package com.example.animalwarterrain.domain.dto;

import com.example.animalwarterrain.domain.entity.BuildType;

public record TileInfo(

        int x,
        int y,
        BuildType buildType,
        Long typeId

) {
}
