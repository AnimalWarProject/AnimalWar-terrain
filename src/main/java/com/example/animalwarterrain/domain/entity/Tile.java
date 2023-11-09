package com.example.animalwarterrain.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tiles")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "terrain_id", nullable = false)
    private Terrain terrain;

    @Enumerated(EnumType.STRING)
    private LandForm landForm;

    private int x;
    private int y;

    @Enumerated(EnumType.STRING)
    private BuildType type;

    private Long typeId;


    public void updateLandForm(LandForm landForm) {
        this.landForm = landForm;
    }
}


