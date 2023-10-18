package com.example.animalwarterrain.client;

import org.apache.catalina.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@FeignClient("USER-SERVICE")
public interface UserClient {
    @PostMapping("api/v1/user/findUser/{userUUID}")
    User findUserByUserUUID(@PathVariable UUID userUUID);
}
