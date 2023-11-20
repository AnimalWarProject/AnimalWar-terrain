package com.example.animalwarterrain.repository;

import com.example.animalwarterrain.domain.entity.Tile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TileRepository extends JpaRepository<Tile, Long> {
}
