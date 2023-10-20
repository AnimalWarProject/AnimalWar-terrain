package com.example.animalwarterrain.kafka;

import com.example.animalwarterrain.domain.dto.TerrainResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TerrainProducer {
    private final KafkaTemplate<String, TerrainResponseDto> kafkaTemplate;

    public void sendTerrainResponseDto(TerrainResponseDto terrainResponseDto) {
        kafkaTemplate.send("terrain-result-topic", terrainResponseDto);
    }

}
