package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.controller;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.Contract;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {

    private final ContractService contractService;

    @Autowired
    public ClientController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping("/contracts")
    public List<Contract> getContractsForLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println("username logged in: " + username);

        return contractService.findContractByClientUsername(username);
    }

}
