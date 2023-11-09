package com.example.animalwarterrain.kafka;

import com.example.animalwarterrain.service.TerrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateTerrainConsumer {

    private final TerrainService terrainService;

    @KafkaListener(topics = "terrain-request-topic", groupId = "animalwar-consumer")
    public void consumeTerrainRequest(UUID userUUID) {
        terrainService.updateTerrain(userUUID);
    }
}