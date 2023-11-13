package com.example.animalwarterrain.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="terrains")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Terrain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private UUID userUUID;

    @Enumerated(EnumType.STRING)
    private LandForm dominantLandForm;

    @OneToMany(mappedBy = "terrain", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tile> tiles;

    private int sea;
    private int land;
    private int mountain;


    public void updateDominantLandForm(LandForm dominantLandForm) {
        this.dominantLandForm = dominantLandForm;
    }

    public void updateSeaCount(int sea){
        this.sea = sea;
    }

    public void updateLandCount(int land){
        this.land = land;
    }
    public void updateMountainCount(int mountain){
        this.mountain = mountain;
    }


}