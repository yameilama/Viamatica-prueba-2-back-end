package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    private Date paymentDate;
    @ManyToOne
    @JoinColumn(name = "client_clientId", referencedColumnName = "clientId")
    private Client client;

}
