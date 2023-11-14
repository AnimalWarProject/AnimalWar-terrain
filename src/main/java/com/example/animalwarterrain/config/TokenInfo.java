package com.example.animalwarterrain.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
@Builder @Getter
public class TokenInfo {
    private String userUUID;
    private String id;
    private String nickName;
}

