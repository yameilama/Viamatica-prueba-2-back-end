package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.UserCaja;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.UserCajaWorking;

import java.util.List;

public interface UserCajaWorkingService {
    void assignUserToCaja(Long userId, Long cajaId);
    List<UserCajaWorking> findAll();

    List<UserCajaWorking> findWorkingUsersForCaja(Long cajaId);

    void updateWorkingUsers(Long cajaId, List<Long> userIds);

}
