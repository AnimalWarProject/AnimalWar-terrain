package com.example.animalwarterrain.repository;

import com.example.animalwarterrain.domain.entity.Terrain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TerrainRepository extends JpaRepository<Terrain, Long> {

    Optional<Terrain> findByUserUUID(UUID userUUID);

}
