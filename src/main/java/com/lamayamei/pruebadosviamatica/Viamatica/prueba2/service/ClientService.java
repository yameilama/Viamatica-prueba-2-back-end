package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.Client;

import java.util.Optional;

public interface ClientService {
    Optional<Client> findByUsername(String username);

}
