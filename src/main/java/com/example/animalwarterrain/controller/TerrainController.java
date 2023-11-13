package com.example.animalwarterrain.controller;


import com.example.animalwarterrain.domain.dto.TerrainRequestDto;
import com.example.animalwarterrain.domain.entity.Tile;
import com.example.animalwarterrain.service.TerrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/terrain")
public class TerrainController {

   private final TerrainService terrainService;

    @PostMapping("/")
    public void generateRandomTerrain(@RequestBody TerrainRequestDto request) {
        terrainService.firstTerrain(request.uuid());
    }

    @GetMapping("/{userUUID}")
    public ResponseEntity<List<Tile>> getTilesByUserUUID(@PathVariable UUID userUUID) {
        List<Tile> tiles = terrainService.getTilesByUserUUID(userUUID);
        return ResponseEntity.ok(tiles);
    }

}