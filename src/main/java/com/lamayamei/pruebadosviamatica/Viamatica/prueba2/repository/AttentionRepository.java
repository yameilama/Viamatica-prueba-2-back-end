package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.Attention;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttentionRepository extends JpaRepository<Attention, Long> {
    List<Attention> findByClient(Client client);
}
