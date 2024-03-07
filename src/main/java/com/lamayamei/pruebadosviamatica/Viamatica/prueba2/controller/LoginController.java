package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.controller;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.DTO.LoginDTO;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.response.JwtAuthenticationResponse;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    private final LoginService loginService;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDTO) {
        try {
            JwtAuthenticationResponse response = loginService.authenticate(loginDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Autenticación fallida: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
