package com.example.animalwarterrain.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Tile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tileId;

    private int x;
    private int y;

    @Enumerated(EnumType.STRING)
    private BuildType type;

    private Long typeId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Terrain terrain;


    public Tile updateTile(BuildType type, Long typeId){
        this.type=type;
        this.typeId = typeId;

        return this;
    }

}
