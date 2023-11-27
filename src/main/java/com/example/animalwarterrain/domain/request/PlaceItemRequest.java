package com.example.animalwarterrain.domain.request;

import com.example.animalwarterrain.domain.entity.ObjectType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PlaceItemRequest {
    private Long tileId;
    private ObjectType objectType;
    private Long objectId;

}
