package com.example.animalwarterrain.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Table(name="freeDistributes")
@AllArgsConstructor
@NoArgsConstructor
public class FreeDistribute {
    @Id
    private UUID userUUID;
    private int FreeDistributeNum;
}
