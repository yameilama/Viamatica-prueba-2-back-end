package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.controller;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.UserCaja;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.UserCajaWorking;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.UserCajaWorkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/caja/users-working")
public class UserCajaWorkingController {

    private final UserCajaWorkingService userCajaWorkingService;

    @Autowired
    public UserCajaWorkingController(UserCajaWorkingService userCajaWorkingService){
        this.userCajaWorkingService = userCajaWorkingService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<UserCajaWorking>> findAllUserCajasWorking(){
        List<UserCajaWorking> userCajasWorking = userCajaWorkingService.findAll();
        return ResponseEntity.ok(userCajasWorking);
    }

    @GetMapping("/{cajaId}")
    public ResponseEntity<List<UserCajaWorking>> findWorkingUsersForCaja(@PathVariable Long cajaId) {
        List<UserCajaWorking> workingUsers = userCajaWorkingService.findWorkingUsersForCaja(cajaId);
        return ResponseEntity.ok(workingUsers);
    }
}
