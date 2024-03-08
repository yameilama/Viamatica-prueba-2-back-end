package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.UserCaja;

import java.util.List;

public interface UserCajaService {
    void assignUserToCaja(Long userId, Long cajaId);
    void setUserActive(Long userId, Long cajaId, boolean isActive);

    List<UserCaja> findAll();
    List<UserCaja> findAssignedUsersForCaja(Long cajaId);

    void updateAssignedUsers(Long cajaId, List<Long> userIds);

}
