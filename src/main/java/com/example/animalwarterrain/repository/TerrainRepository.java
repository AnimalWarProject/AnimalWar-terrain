package com.example.animalwarterrain.repository;

import com.example.animalwarterrain.domain.entity.Terrain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TerrainRepository extends JpaRepository<Terrain, Long> {
}
