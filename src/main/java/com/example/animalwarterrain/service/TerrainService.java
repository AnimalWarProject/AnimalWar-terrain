package com.example.animalwarterrain.service;

import com.example.animalwarterrain.domain.dto.TerrainResponseDto;
import com.example.animalwarterrain.domain.entity.LandForm;
import com.example.animalwarterrain.domain.entity.Terrain;
import com.example.animalwarterrain.domain.entity.Tile;
import com.example.animalwarterrain.kafka.ResultTerrainProducer;
import com.example.animalwarterrain.repository.TerrainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@RequiredArgsConstructor
public class TerrainService {

    private final ResultTerrainProducer resultTerrainProducer;
    private final TerrainRepository terrainRepository;


    // 회원가입한 사람 최초로 맵 생성
    @Transactional
    public void firstTerrain(UUID userUUID) {
        Terrain terrain = Terrain.builder()
                .userUUID(userUUID)
                .dominantLandForm(null)
                .tiles(new ArrayList<>())
                .sea(0)
                .land(0)
                .mountain(0)
                .build();

        int seaCount = 0;
        int landCount = 0;
        int mountainCount = 0;

        for (int i = 0; i < 100; i++) {
            LandForm randomLandForm = LandForm.values()[new Random().nextInt(LandForm.values().length)];
            Tile tile = Tile.builder()
                    .terrain(terrain)
                    .landForm(randomLandForm)
                    .x(i % 10)
                    .y(i / 10)
                    .build();

            terrain.getTiles().add(tile);

            if (randomLandForm == LandForm.SEA) {
                seaCount++;
            } else if (randomLandForm == LandForm.LAND) {
                landCount++;
            } else if (randomLandForm == LandForm.MOUNTAIN) {
                mountainCount++;
            }
        }

        LandForm dominantLandForm;
        if (seaCount >= landCount && seaCount >= mountainCount) {
            dominantLandForm = LandForm.SEA;
        } else if (landCount >= seaCount && landCount >= mountainCount) {
            dominantLandForm = LandForm.LAND;
        } else {
            dominantLandForm = LandForm.MOUNTAIN;
        }

        terrain.updateDominantLandForm(dominantLandForm);
        terrain.updateSeaCount(seaCount);
        terrain.updateLandCount(landCount);
        terrain.updateMountainCount(mountainCount);

        // CascadeType.ALL로 인해 Tile도 같이 저장됨
        terrainRepository.save(terrain);

        TerrainResponseDto terrainResponseDto = new TerrainResponseDto(userUUID, dominantLandForm, seaCount, landCount, mountainCount);

        resultTerrainProducer.sendTerrainResponseDto(terrainResponseDto);
    }




    @Transactional
    public void updateTerrain(UUID userUUID) {
        Terrain terrain = terrainRepository.findByUserUUID(userUUID)
                .orElseThrow(() -> new RuntimeException("해당하는 유저의 Terrain이 없습니다"));

        Map<LandForm, Long> landFormCount = resetLandFormCount();

        Random random = new Random();
        int seaCount = 0;
        int mountainCount = 0;
        int landCount = 0;

        for (Tile tile : terrain.getTiles()) {
            LandForm newLandForm = LandForm.values()[random.nextInt(LandForm.values().length)];
            tile.updateLandForm(newLandForm);
            landFormCount.put(newLandForm, landFormCount.get(newLandForm) + 1);

            // 해당 타일의 랜드폼에 따라 sea, mountain, land 카운트를 증가시킴
            if (newLandForm == LandForm.SEA) {
                seaCount++;
            } else if (newLandForm == LandForm.MOUNTAIN) {
                mountainCount++;
            } else if (newLandForm == LandForm.LAND) {
                landCount++;
            }
        }

        LandForm dominantLandForm = landFormCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();
        terrain.updateDominantLandForm(dominantLandForm);

        // sea, mountain, land 값을 업데이트
        terrain.updateSeaCount(seaCount);
        terrain.updateMountainCount(mountainCount);
        terrain.updateLandCount(landCount);

        terrainRepository.save(terrain);

        // TerrainResponseDto 객체를 생성하여 Kafka를 통해 전송합니다.
        TerrainResponseDto terrainResponseDto = new TerrainResponseDto(userUUID, dominantLandForm, seaCount, landCount, mountainCount);
        resultTerrainProducer.sendTerrainResponseDto(terrainResponseDto);
    }

    private Map<LandForm, Long> resetLandFormCount() {
        // EnumMap은 enum 키를 사용할 때 최적화된 Map 구현
        Map<LandForm, Long> landFormCount = new EnumMap<>(LandForm.class);
        for (LandForm form : LandForm.values()) {
            landFormCount.put(form, 0L);
        }
        return landFormCount;
    }

    // 상대 타일 정보 검색
    public List<Tile> getTilesByUserUUID(UUID userUUID) {
        return terrainRepository.findByUserUUID(userUUID).map(Terrain::getTiles)
                .orElseThrow(() -> new NoSuchElementException("해당 사용자 UUID에 대한 타일 정보가 없습니다."));
    }

}
