package com.example.animalwarterrain.repository;

import com.example.animalwarterrain.domain.entity.Tile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TileRepository extends JpaRepository<Tile,Long> {

    @Query("SELECT t FROM Tile t " +
            "JOIN FETCH Terrain tr on t.terrain.userUUID=tr.userUUID " +
            "WHERE tr.userUUID =:uuid order by t.tileId asc")
    Optional<List<Tile>> findTilesByUUID(UUID uuid);
}
