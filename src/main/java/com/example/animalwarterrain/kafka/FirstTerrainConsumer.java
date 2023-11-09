package com.example.animalwarterrain.kafka;

import com.example.animalwarterrain.service.TerrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FirstTerrainConsumer {

    private final TerrainService terrainService;

    @KafkaListener(topics = "first-terrain-request-topic", groupId = "animalwar-terrain-group")
    public void consumeFirstTerrainRequest(String userUUID) {
        terrainService.firstTerrain(UUID.fromString(userUUID));
    }
}