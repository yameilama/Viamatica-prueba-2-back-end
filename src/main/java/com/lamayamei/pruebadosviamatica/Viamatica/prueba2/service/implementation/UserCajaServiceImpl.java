package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.implementation;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.Caja;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.User;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.UserCaja;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository.CajaRepository;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository.UserCajaRepository;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository.UserRepository;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.UserCajaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCajaServiceImpl implements UserCajaService {

    private final UserCajaRepository userCajaRepository;
    private final CajaRepository cajaRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserCajaServiceImpl(UserCajaRepository userCajaRepository, CajaRepository cajaRepository, UserRepository userRepository){
        this.userCajaRepository = userCajaRepository;
        this.cajaRepository = cajaRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void assignUserToCaja(Long userId, Long cajaId) {
        Caja caja = cajaRepository.findById(cajaId)
                .orElseThrow(() -> new RuntimeException("Caja no encontrada"));

        long assignedUsersCount = userCajaRepository.countByCajaCajaId(cajaId);

        if (assignedUsersCount >= caja.getMaxUsersAssigned()) {
            throw new RuntimeException("Caja con limite de usuarios asignados");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User no encontrado"));

        UserCaja userCaja = new UserCaja();
        userCaja.setUser(user);
        userCaja.setCaja(caja);
        userCajaRepository.save(userCaja);
    }

    @Override
    public void setUserActive(Long userId, Long cajaId, boolean isActive) {

    }

    @Override
    public List<UserCaja> findAll() {
        return userCajaRepository.findAll();
    }

    @Override
    public List<UserCaja> findAssignedUsersForCaja(Long cajaId) {
        return userCajaRepository.findByCajaCajaId(cajaId);

    }

    @Override
    public void updateAssignedUsers(Long cajaId, List<Long> userIds) {
        List<UserCaja> currentAssignments = userCajaRepository.findByCajaCajaId(cajaId);
        userCajaRepository.deleteAll(currentAssignments);

        for(Long userId : userIds) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            Caja caja = cajaRepository.findById(cajaId)
                    .orElseThrow(() -> new RuntimeException("Caja no encontrada"));
            UserCaja userCaja = new UserCaja();
            userCaja.setUser(user);
            userCaja.setCaja(caja);
            userCajaRepository.save(userCaja);
        }
    }
}
