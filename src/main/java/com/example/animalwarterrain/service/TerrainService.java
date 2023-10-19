package com.example.animalwarterrain.service;

import com.example.animalwarterrain.domain.dto.TerrainResponseDto;
import com.example.animalwarterrain.domain.entity.FreeDistribute;
import com.example.animalwarterrain.domain.entity.LandForm;
import com.example.animalwarterrain.domain.entity.Terrain;
import com.example.animalwarterrain.kafka.TerrainProducer;
import com.example.animalwarterrain.domain.request.TerrainRequest;
import com.example.animalwarterrain.repository.FreeDistributeRepository;
import com.example.animalwarterrain.repository.TerrainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;


@Service
@RequiredArgsConstructor
public class TerrainService {

    private final TerrainProducer terrainProducer;
    private final TerrainRepository terrainRepository;
    private final FreeDistributeRepository freeDistributeRepository;

    public void generateRandomTerrain(TerrainRequest terrainRequest) {
        int requiredGold = 5000;

        FreeDistribute freeDistribute = freeDistributeRepository.findById(terrainRequest.getUserUUID())
                .orElse(new FreeDistribute(terrainRequest.getUserUUID(), 0));

        if (terrainRequest.isFreeRequest()) {
            if (freeDistribute.getFreeDistributeNum() >= 3) {
                throw new IllegalArgumentException("Maximum free requests reached for the day.");
            }
            freeDistribute.setFreeDistributeNum(freeDistribute.getFreeDistributeNum() + 1);
            freeDistributeRepository.save(freeDistribute);
        } else {
            if (terrainRequest.getGold() < requiredGold) {
                throw new IllegalArgumentException("Not enough gold.");
            }
        }
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

        terrainProducer.sendTerrainResponseDto(new TerrainResponseDto(terrainRequest.getUserUUID(), land, sea, mountain, terrain.getLandForm(), -requiredGold));

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
    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void resetFreeDistributeCount() {
        freeDistributeRepository.findAll().forEach(freeDistribute -> {
            freeDistribute.setFreeDistributeNum(0);
            freeDistributeRepository.save(freeDistribute);
        });
    }

}
