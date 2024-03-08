package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.UserCajaWorking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCajaWorkingRepository extends JpaRepository<UserCajaWorking, Long> {
    long countByCajaCajaId(long cajaId);

    Optional<UserCajaWorking> findByUserUserIdAndCajaCajaId(Long userId, Long cajaId);

    List<UserCajaWorking> findByCajaCajaId(Long cajaId);
}
