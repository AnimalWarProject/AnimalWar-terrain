package com.example.animalwarterrain.controller;


import com.example.animalwarterrain.domain.dto.BatchRequest;
import com.example.animalwarterrain.domain.entity.Tile;
import com.example.animalwarterrain.service.TerrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/terrains")
public class TerrainController {

   private final TerrainService terrainService;

    @PostMapping("/generate")
    public void generateRandomTerrain(@RequestBody UUID userUUID) {
        terrainService.generateRandomTerrain(userUUID);
    }

    @PostMapping
    public List<Tile> batchTerrain(@RequestBody BatchRequest batchRequest){
        return terrainService.batchTerrain(batchRequest);
    }

}