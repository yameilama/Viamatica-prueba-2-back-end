package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Attention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attentionId;

    @ManyToOne
    @JoinColumn(name = "turn_turnId", referencedColumnName = "turnId")
    private Turn turn;

    @ManyToOne
    @JoinColumn(name = "client_clientId", referencedColumnName = "clientId")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "attentiontype_attentionTypeId", referencedColumnName = "attentionTypeId")
    private AttentionType attentionType;

    @ManyToOne
    @JoinColumn(name = "attentionstatus_statusId", referencedColumnName = "statusId")
    private AttentionStatus attentionStatus;

}
