package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.AttentionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttentionTypeRepository extends JpaRepository<AttentionType, Long> {
    Optional<AttentionType> findByCode(String code);
}
