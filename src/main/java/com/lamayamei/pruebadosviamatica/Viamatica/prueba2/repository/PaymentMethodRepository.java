package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    Optional<PaymentMethod> findByDescription(String description);
}
