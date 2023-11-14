package com.example.animalwarterrain.domain.response;

import com.example.animalwarterrain.domain.entity.LandForm;
import com.example.animalwarterrain.domain.entity.ObjectType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TileResponse {
    private Long id;
    private LandForm landForm;
    private int x;
    private int y;

    private ObjectType objectType;

    private Long objectId;
}