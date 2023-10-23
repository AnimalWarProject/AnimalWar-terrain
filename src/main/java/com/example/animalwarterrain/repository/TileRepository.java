package com.example.animalwarterrain.repository;

import com.example.animalwarterrain.domain.entity.Tile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TileRepository extends JpaRepository<Tile,Long> {

    List<Tile> findAllByTerrain_UserUUIDOrderByTileIdAsc(UUID uuid);
}
