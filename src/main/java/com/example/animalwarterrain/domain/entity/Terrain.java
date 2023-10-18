package com.example.animalwarterrain.domain.entity;

import jakarta.persistence.*;
import lombok.*;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long terrainId;

    private int sea;
    private int mountain;
    private int land;

    private UUID userUUID;

    private LandForm landForm;

}
