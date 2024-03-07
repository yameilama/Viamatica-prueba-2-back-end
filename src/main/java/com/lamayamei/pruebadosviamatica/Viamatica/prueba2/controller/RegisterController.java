package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.controller;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.DTO.RegisterDTO;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
@CrossOrigin(origins = "http://localhost:4200")
public class RegisterController {

    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService){
        this.registerService = registerService;
    }

    @PostMapping
    public ResponseEntity<?> registerUser(@Validated @RequestBody RegisterDTO registerDTO) {
        registerService.register(registerDTO);
        return ResponseEntity.ok().body("Usuario registrado exitosamente.");
    }

}
