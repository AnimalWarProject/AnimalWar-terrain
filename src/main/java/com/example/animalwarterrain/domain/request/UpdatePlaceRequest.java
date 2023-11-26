package com.example.animalwarterrain.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePlaceRequest {
    private List<PlaceItemRequest> placeItems;
    private List<Long> removeItems;
}
