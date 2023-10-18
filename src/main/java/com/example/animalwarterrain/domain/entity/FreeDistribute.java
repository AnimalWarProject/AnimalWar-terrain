package com.example.animalwarterrain.domain.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class FreeDistribute {
    private UUID userUUID;
    private int FreeDistributeNum;
}
