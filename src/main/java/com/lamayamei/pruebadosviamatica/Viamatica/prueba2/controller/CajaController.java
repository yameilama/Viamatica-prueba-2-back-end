package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.controller;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.DTO.UpdateCajaDTO;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.Caja;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.CajaService;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.UserCajaService;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.UserCajaWorkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/caja")
public class CajaController {

    private final CajaService cajaService;
    private final UserCajaService userCajaService;
    private final UserCajaWorkingService userCajaWorkingService;

    @Autowired
    public CajaController(CajaService cajaService, UserCajaService userCajaService,UserCajaWorkingService userCajaWorkingService){
        this.cajaService = cajaService;
        this.userCajaService = userCajaService;
        this.userCajaWorkingService = userCajaWorkingService;
    }

    @GetMapping("/all")
    public List<Caja> getAll(){
        return cajaService.findAll();
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateUsersInCaja(@RequestBody UpdateCajaDTO updateCajaDTO){
        try {
            userCajaService.updateAssignedUsers(updateCajaDTO.getCajaId(), updateCajaDTO.getAssignedUserIds());

            userCajaWorkingService.updateWorkingUsers(updateCajaDTO.getCajaId(), updateCajaDTO.getWorkingUserIds());

            return ResponseEntity.ok("Caja users actualizado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
