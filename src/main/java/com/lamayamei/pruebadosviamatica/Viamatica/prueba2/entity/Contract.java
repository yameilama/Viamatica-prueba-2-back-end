package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractId;
    private Date startDate;
    private Date endDate;
    @ManyToOne
    @JoinColumn(name = "product_productId", referencedColumnName = "productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "statuscontract_statusId", referencedColumnName = "statusId")
    private StatusContract statusContract;

    @ManyToOne
    @JoinColumn(name = "client_clientId", referencedColumnName = "clientId")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "methodpayment_methodpaymentId", referencedColumnName = "paymentMethodId")
    private PaymentMethod paymentMethod;


}
