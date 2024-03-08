package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserCajaWorking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_userId", referencedColumnName = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "caja_cajaId", referencedColumnName = "cajaId")
    private Caja caja;
}
