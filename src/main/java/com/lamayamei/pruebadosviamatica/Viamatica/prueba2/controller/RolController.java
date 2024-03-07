package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.controller;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.Rol;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.RolService;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rol")
@CrossOrigin(origins = "http://localhost:4200")
public class RolController {

    private final RolService rolService;

    @Autowired
    public RolController(RolService rolService){
        this.rolService = rolService;
    }

    @GetMapping("/all")
    public List<Rol> getAllRoles(){
        return rolService.findAllRoles();
    }

}
