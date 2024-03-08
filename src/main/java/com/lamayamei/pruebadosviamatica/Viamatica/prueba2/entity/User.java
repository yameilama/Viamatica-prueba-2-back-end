package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String email;
    private String password;
    private boolean active = true;


    @Column(name = "date_created")
    @CreationTimestamp
    private Date dateCreated;
    @ManyToOne
    @JoinColumn(name = "rol_rolId", referencedColumnName = "rolId")
    private Rol rol;

    @OneToMany(mappedBy = "user")
    private Set<UserCaja> userCajas;


}
