package com.example.animalwarterrain.controller;

import com.example.animalwarterrain.domain.entity.Terrain;
import com.example.animalwarterrain.domain.request.TerrainRequest;
import com.example.animalwarterrain.service.TerrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/maps")
public class TerrainController {

   private final TerrainService terrainService;

    @PostMapping("/generate")
    public Terrain generateMap(@RequestBody TerrainRequest request) {
        return terrainService.generateRandomMap(request.getUserUUID(), request.getGold());
    }
}