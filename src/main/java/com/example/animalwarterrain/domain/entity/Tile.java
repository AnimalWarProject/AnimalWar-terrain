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
    private ObjectType objectType;

    private Long objectId;


    public void updateLandForm(LandForm landForm) {
        this.landForm = landForm;
    }


    public void placeObject(ObjectType objectType, Long objectId) {
        this.objectType = objectType;
        this.objectId = objectId;
    }

    public void removeObject() {
        // 객체를 제거하는 로직 구현
        this.objectType = null;
        this.objectId = null;
    }
}


