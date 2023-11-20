package com.example.animalwarterrain.domain.request;

import com.example.animalwarterrain.domain.entity.ObjectType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PlaceItemRequest {
    private Long tileId;    // 아이템을 배치할 타일 ID
    private ObjectType objectType;  // 배치할 아이템의 타입
    private Long objectId;  // 배치할 아이템의 ID

}
