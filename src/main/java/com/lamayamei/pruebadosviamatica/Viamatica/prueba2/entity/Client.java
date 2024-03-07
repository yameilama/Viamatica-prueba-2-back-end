package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;
    private String name;
    private String lastName;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String identification;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private String addressReference;
    @ManyToOne
    @JoinColumn(name = "rol_rolId", referencedColumnName = "rolId")
    private Rol rol;

}
