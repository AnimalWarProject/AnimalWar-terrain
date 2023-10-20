package com.example.animalwarterrain.service;

import com.example.animalwarterrain.domain.dto.TerrainResponseDto;
import com.example.animalwarterrain.domain.entity.LandForm;
import com.example.animalwarterrain.domain.entity.Terrain;
import com.example.animalwarterrain.kafka.ResultTerrainProducer;
import com.example.animalwarterrain.repository.TerrainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class TerrainService {

    private final ResultTerrainProducer resultTerrainProducer;
    private final TerrainRepository terrainRepository;

    public void generateRandomTerrain(UUID userUUID) {
        Random rand = new Random();
        int land = rand.nextInt(100);
        int sea = rand.nextInt(100 - land);
        int mountain = 100 - land - sea;

        Terrain terrain = new Terrain();
        terrain.setUserUUID(userUUID);
        terrain.setLand(land);
        terrain.setSea(sea);
        terrain.setMountain(mountain);
        terrain.setLandForm(determineLandForm(land, sea, mountain));

        terrain = terrainRepository.save(terrain);

        resultTerrainProducer.sendTerrainResponseDto(new TerrainResponseDto(userUUID, terrain.getLandForm()));

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

}
