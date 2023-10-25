package com.example.animalwarterrain.service;

import com.example.animalwarterrain.domain.dto.BatchRequest;
import com.example.animalwarterrain.domain.dto.TerrainResponseDto;
import com.example.animalwarterrain.domain.dto.TileInfo;
import com.example.animalwarterrain.domain.entity.LandForm;
import com.example.animalwarterrain.domain.entity.Terrain;
import com.example.animalwarterrain.domain.entity.Tile;
import com.example.animalwarterrain.kafka.ResultTerrainProducer;
import com.example.animalwarterrain.repository.TerrainRepository;
import com.example.animalwarterrain.repository.TileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class TerrainService {

    private final ResultTerrainProducer resultTerrainProducer;
    private final TerrainRepository terrainRepository;
    private final TileRepository tileRepository;


    public void generateRandomTerrain(UUID userUUID) {
        Random rand = new Random();
        int land = rand.nextInt(100);
        int sea = rand.nextInt(100 - land);
        int mountain = 100 - land - sea;
        LandForm determinedLandForm = determineLandForm(land, sea, mountain);

        Terrain terrain = Terrain.buildTerrain(userUUID, land, sea, mountain, determinedLandForm);

        terrain = terrainRepository.save(terrain);

        resultTerrainProducer.sendTerrainResponseDto(new TerrainResponseDto(userUUID, terrain.getLandForm()));

        //디폴트 타일 10*10  100개 세팅
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){

                // X, Y값만 넣어놓기
                tileRepository.save(Tile.builder()
                        .x(i)
                        .y(j)
                        .terrain(terrain)
                        .build());
            }
        }

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

    public List<Tile> batchTerrain(BatchRequest batchRequest) {

        // 데이터 정렬이  X 0  y 0~10
        //        X 1  y 0~10 이라고 가정
        List<Tile> tiles = tileRepository.findTilesByUUID(batchRequest.uuid())
                .orElseThrow(()->new RuntimeException("No UUID or Tile  Error"));

        // 데이터 정렬이  X 0  y 0~10
        //        X 1  y 0~10 이라고 가정
        List<TileInfo> tileInfos = batchRequest.tileInfos();

        for(int i=0;i<tileInfos.size();i++){
            Tile tile = tiles.get(i);
            TileInfo tileInfo = tileInfos.get(i);

            tile.updateTile(tileInfo.buildType(),tileInfo.typeId());
        }


        return tiles;

    }
}
