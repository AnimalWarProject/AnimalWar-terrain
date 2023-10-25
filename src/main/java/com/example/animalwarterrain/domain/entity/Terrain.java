package com.example.animalwarterrain.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="terrains")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Terrain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long terrainId;

    private UUID userUUID;
    private int sea;
    private int mountain;
    private int land;
//
//    @OneToMany(mappedBy = "terrain")
//    private List<Tile> tiles;

    @Enumerated(value = EnumType.STRING)
    private LandForm landForm;

    public static Terrain buildTerrain(UUID userUUID, int land, int sea, int mountain, LandForm landForm) {
        return Terrain.builder()
                .userUUID(userUUID)
                .land(land)
                .sea(sea)
                .mountain(mountain)
                .landForm(landForm)
                .build();
    }

}
