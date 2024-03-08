package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.controller;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.DTO.AssignUserToCajaDTO;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.UserCaja;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.UserCajaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/caja")
public class UserCajaController {

    private final UserCajaService userCajaService;

    @Autowired
    public UserCajaController(UserCajaService userCajaService){
        this.userCajaService = userCajaService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserCaja>> findAllUserCajas(){
        List<UserCaja> userCajas = userCajaService.findAll();
        return ResponseEntity.ok(userCajas);
    }

    @GetMapping("/{cajaId}")
    public ResponseEntity<List<UserCaja>> findAssignedUsersForCaja(@PathVariable Long cajaId) {
        List<UserCaja> assignedUsers = userCajaService.findAssignedUsersForCaja(cajaId);
        return ResponseEntity.ok(assignedUsers);
    }

    @PostMapping("/add")
    public ResponseEntity<?> assignUserToCaja(@RequestBody AssignUserToCajaDTO request) {
        try {
            userCajaService.assignUserToCaja(request.getUserId(), request.getCajaId());
            return ResponseEntity.ok().body("Usuario asignado a caja.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
