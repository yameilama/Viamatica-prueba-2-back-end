package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentMethodId;
    private String description;

}
