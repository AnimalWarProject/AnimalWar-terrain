package com.example.animalwarterrain.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class FreeDistribute {
    private UUID userUUID;
    private int FreeDistributeNum;
}
