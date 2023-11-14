package com.example.animalwarterrain.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    public TokenInfo parseAccessToken(String accessToken) {
        Claims body = Jwts.parserBuilder()
                .setSigningKey(secret.getBytes())
                .build()
                .parseClaimsJws(accessToken)
                .getBody();

        return TokenInfo.builder()
                .userUUID(body.get("userUUID", String.class))
                .id(body.get("id", String.class))
                .nickName(body.get("nickName", String.class))
                .build();
    }
}
