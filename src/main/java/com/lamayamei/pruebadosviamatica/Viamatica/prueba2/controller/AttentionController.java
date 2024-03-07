package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.controller;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.DTO.AttentionDTO;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.Attention;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.AttentionType;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.AttentionService;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.AttentionTypeService;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attention-type")
@CrossOrigin(origins = "http://localhost:4200")
public class AttentionController {
    private final AttentionTypeService attentionTypeService;
    private final AttentionService attentionService;
    private final ClientService clientService;


    @Autowired
    public AttentionController(AttentionTypeService attentionTypeService, AttentionService attentionService, ClientService clientService) {
        this.attentionTypeService = attentionTypeService;
        this.attentionService = attentionService;
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<AttentionType>> getAllAttentionTypes() {
        List<AttentionType> types = attentionTypeService.findAll();
        return ResponseEntity.ok(types);
    }

    @GetMapping("/{code}")
    public ResponseEntity<AttentionType> getAttentionTypeByCode(@PathVariable String code) {
        return attentionTypeService.findByCode(code)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/generate")
    public ResponseEntity<Attention> generateAttention(@RequestBody AttentionDTO attention){
        System.out.println("Received DTO: " + attention);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println("username in AttentionController " + username);
        Long clientId = clientService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"))
                .getClientId();
        Attention createdAttention = attentionService.createAttention(clientId, attention.getAttentionTypeId());

        return ResponseEntity.ok(createdAttention);
    }

    @GetMapping("/my-attention")
    public ResponseEntity<List<Attention>> getMyAttention() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Long clientId = clientService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"))
                .getClientId();
        List<Attention> attentions = attentionService.getAttentionsForClient(clientId);
        return ResponseEntity.ok(attentions);
    }

}
