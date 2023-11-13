package com.example.animalwarterrain.kafka;

import com.example.animalwarterrain.domain.dto.TerrainResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResultTerrainProducer {
    private final KafkaTemplate<String, TerrainResponseDto> kafkaTemplate;

    public void sendTerrainResponseDto(TerrainResponseDto terrainResponseDto) {
        kafkaTemplate.send(TopicConfig.resultTerrain, terrainResponseDto.getUserUUID().toString(), terrainResponseDto);
    }



}
