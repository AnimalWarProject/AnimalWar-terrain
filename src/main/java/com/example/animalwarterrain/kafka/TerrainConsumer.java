package com.example.animalwarterrain.kafka;

import com.example.animalwarterrain.domain.request.TerrainRequest;
import com.example.animalwarterrain.service.TerrainService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TerrainConsumer {

    private final TerrainService terrainService;


    @KafkaListener(topics = "terrain-request-topic", groupId = "animalwar-consumer")
    public void consumeTerrainRequest(ConsumerRecord<String, TerrainRequest> record) {
        TerrainRequest request = record.value();
        terrainService.generateRandomTerrain(request);
    }
}