package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class AttentionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attentionTypeId;
    private String description;
    private String code;
}
