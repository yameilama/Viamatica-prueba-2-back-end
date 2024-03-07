package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductName(String productName);
}
