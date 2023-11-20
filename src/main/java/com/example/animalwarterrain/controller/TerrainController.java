package com.example.animalwarterrain.controller;


import com.example.animalwarterrain.config.JwtService;
import com.example.animalwarterrain.config.TokenInfo;
import com.example.animalwarterrain.domain.dto.TerrainRequestDto;
import com.example.animalwarterrain.domain.entity.Tile;
import com.example.animalwarterrain.domain.request.PlaceItemRequest;
import com.example.animalwarterrain.domain.response.TileResponse;
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
    private final JwtService jwtService;

    @PostMapping("/")
    public void generateRandomTerrain(@RequestBody TerrainRequestDto terrainRequestDto) {
        terrainService.firstTerrain(terrainRequestDto.getUserUUID());
    }

    @GetMapping("/{userUUID}")
    public ResponseEntity<List<Tile>> getTilesByUserUUID(@PathVariable UUID userUUID) {
        List<Tile> tiles = terrainService.getTilesByUserUUID(userUUID);
        return ResponseEntity.ok(tiles);
    }


    @GetMapping("/myTile")
    public ResponseEntity<List<TileResponse>> getMyTile(@RequestHeader("Authorization") String accessToken) {
        TokenInfo tokenInfo = jwtService.parseAccessToken(accessToken.replace("Bearer ", ""));
        UUID userUUID = UUID.fromString(tokenInfo.getUserUUID());
        List<TileResponse> tileResponses = terrainService.getMyTile(userUUID);
        return ResponseEntity.ok(tileResponses);
    }

    @PostMapping("/placeItems")
    public ResponseEntity<?> placeItems(@RequestBody List<PlaceItemRequest> requests) {
        terrainService.placeItems(requests);
        return ResponseEntity.ok().build();
    }

}