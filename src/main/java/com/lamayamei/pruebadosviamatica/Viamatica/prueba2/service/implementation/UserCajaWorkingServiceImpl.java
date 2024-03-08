package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.implementation;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.Caja;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.User;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.UserCaja;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.UserCajaWorking;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository.CajaRepository;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository.UserCajaWorkingRepository;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository.UserRepository;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.UserCajaWorkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCajaWorkingServiceImpl implements UserCajaWorkingService {

    private final CajaRepository cajaRepository;
    private final UserRepository userRepository;
    private final UserCajaWorkingRepository userCajaWorkingRepository;

    @Autowired
    public UserCajaWorkingServiceImpl(CajaRepository cajaRepository,UserRepository userRepository,UserCajaWorkingRepository userCajaWorkingRepository ){
    this.cajaRepository = cajaRepository;
    this.userRepository = userRepository;
    this.userCajaWorkingRepository = userCajaWorkingRepository;
    }

    @Override
    public void assignUserToCaja(Long userId, Long cajaId) {
        Caja caja = cajaRepository.findById(cajaId)
                .orElseThrow(() -> new RuntimeException("Caja no encontrada"));

        long assignedUsersCount = userCajaWorkingRepository.countByCajaCajaId(cajaId);

        if (assignedUsersCount >= caja.getMaxUsersAssigned()) {
            throw new RuntimeException("Caja con limite de usuarios trabajando asignados");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User no encontrado"));

        UserCajaWorking userCajaWorking = new UserCajaWorking();
        userCajaWorking.setUser(user);
        userCajaWorking.setCaja(caja);
        userCajaWorkingRepository.save(userCajaWorking);
    }

    @Override
    public List<UserCajaWorking> findAll() {
        return userCajaWorkingRepository.findAll();
    }

    @Override
    public List<UserCajaWorking> findWorkingUsersForCaja(Long cajaId) {
        return userCajaWorkingRepository.findByCajaCajaId(cajaId);
    }

    @Override
    public void updateWorkingUsers(Long cajaId, List<Long> userIds) {
        List<UserCajaWorking> currentAssignments = userCajaWorkingRepository.findByCajaCajaId(cajaId);
        userCajaWorkingRepository.deleteAll(currentAssignments);

        for(Long userId : userIds) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            Caja caja = cajaRepository.findById(cajaId)
                    .orElseThrow(() -> new RuntimeException("Caja no encontrada"));
            UserCajaWorking UserCajaWorking = new UserCajaWorking();
            UserCajaWorking.setUser(user);
            UserCajaWorking.setCaja(caja);
            userCajaWorkingRepository.save(UserCajaWorking);
        }
    }
}
