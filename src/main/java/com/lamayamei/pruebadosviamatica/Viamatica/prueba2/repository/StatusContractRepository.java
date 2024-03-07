package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.StatusContract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusContractRepository extends JpaRepository<StatusContract, Long> {

    Optional<StatusContract> findByDescription(String description);

}
