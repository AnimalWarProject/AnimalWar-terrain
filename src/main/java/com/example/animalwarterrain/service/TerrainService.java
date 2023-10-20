package com.example.animalwarterrain.service;

import com.example.animalwarterrain.domain.dto.TerrainResponseDto;
import com.example.animalwarterrain.domain.entity.LandForm;
import com.example.animalwarterrain.domain.entity.Terrain;
import com.example.animalwarterrain.kafka.TerrainProducer;
import com.example.animalwarterrain.domain.request.TerrainRequest;
import com.example.animalwarterrain.repository.TerrainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
@RequiredArgsConstructor
public class TerrainService {

    private final TerrainProducer terrainProducer;
    private final TerrainRepository terrainRepository;

    public void generateRandomTerrain(TerrainRequest terrainRequest) {
        Random rand = new Random();
        int land = rand.nextInt(100);
        int sea = rand.nextInt(100 - land);
        int mountain = 100 - land - sea;

        Terrain terrain = new Terrain();
        terrain.setUserUUID(terrainRequest.getUserUUID());
        terrain.setLand(land);
        terrain.setSea(sea);
        terrain.setMountain(mountain);
        terrain.setLandForm(determineLandForm(land, sea, mountain));

        terrain = terrainRepository.save(terrain);

        terrainProducer.sendTerrainResponseDto(new TerrainResponseDto(terrainRequest.getUserUUID(), terrain.getLandForm()));

    }

    private LandForm determineLandForm(int land, int sea, int mountain) {
        if (land >= sea && land >= mountain) {
            return LandForm.LAND;
        } else if (sea > land && sea >= mountain) {
            return LandForm.SEA;
        } else {
            return LandForm.MOUNTAIN;
        }
    }

    // 매일 자정 3회 무료 초기화
}
