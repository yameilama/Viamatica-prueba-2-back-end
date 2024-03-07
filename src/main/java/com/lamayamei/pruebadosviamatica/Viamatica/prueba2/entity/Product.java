package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private String productDescription;
    private String price;
    private String speed;
    @OneToMany(mappedBy = "product")
    private Set<Contract> contracts;

}
