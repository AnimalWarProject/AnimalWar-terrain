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

    private LandForm landForm;



}
