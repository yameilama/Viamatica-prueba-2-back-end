package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.UserCaja;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCajaRepository extends JpaRepository<UserCaja, Long> {

    long countByCajaCajaId(long cajaId);

    Optional<UserCaja> findByUserUserIdAndCajaCajaId(Long userId, Long cajaId);

    List<UserCaja> findByCajaCajaId(Long cajaId);

}
