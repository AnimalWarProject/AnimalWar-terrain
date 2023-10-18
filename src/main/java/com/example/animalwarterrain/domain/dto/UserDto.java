package com.example.animalwarterrain.domain.dto;

import com.example.animalwarterrain.domain.entity.LandForm;
import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {
    private UUID userUUID;

    private String id;
    private String nickName;
    private int gold;

    private LandForm landForm;
    private int land;
    private int sea;
    private int mountain;
}
