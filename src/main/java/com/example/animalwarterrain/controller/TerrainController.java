package com.example.animalwarterrain.controller;


import com.example.animalwarterrain.domain.dto.TerrainRequestDto;
import com.example.animalwarterrain.service.TerrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/terrain")
public class TerrainController {

   private final TerrainService terrainService;

    @PostMapping("/")
    public void generateRandomTerrain(@RequestBody TerrainRequestDto request) {
        terrainService.generateRandomTerrain(request.uuid());
    }

    @PostMapping("/batch")
    public List<Tile> batchTerrain(@RequestBody PlaceRequest placeRequest){
        return terrainService.batchTerrain(placeRequest);
    }

}