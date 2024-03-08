package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Turn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long turnId;
    private String description;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "caja_cajaId", referencedColumnName = "cajaId")
    private Caja caja;

    @ManyToOne
    @JoinColumn(name = "user_userId", referencedColumnName = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "client_clientId", referencedColumnName = "clientId")
    private Client client;
}
