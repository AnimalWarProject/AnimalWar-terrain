package com.example.animalwarterrain.repository;

import com.example.animalwarterrain.domain.entity.FreeDistribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FreeDistributeRepository extends JpaRepository<FreeDistribute, UUID> {
}
