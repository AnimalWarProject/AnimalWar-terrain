package com.example.animalwarterrain.kafka;

import com.example.animalwarterrain.domain.dto.TerrainRequestDto;
import com.example.animalwarterrain.service.TerrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateTerrainConsumer {

    private final TerrainService terrainService;

    @KafkaListener(topics = "user-terrain-request-topic", groupId = "User")
    public void consumeTerrainRequest(TerrainRequestDto terrainRequestDto) {
        terrainService.updateTerrain(terrainRequestDto.getUserUUID());
    }
}