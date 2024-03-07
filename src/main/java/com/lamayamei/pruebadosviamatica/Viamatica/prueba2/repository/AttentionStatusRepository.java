package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.AttentionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttentionStatusRepository extends JpaRepository<AttentionStatus, Long> {
    Optional<AttentionStatus> findByCode(String code);
}
