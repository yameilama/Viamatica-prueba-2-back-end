package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.controller;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.DTO.AssignCajaTurnoDTO;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.Caja;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.Turn;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turno")
public class TurnoController {


    private final TurnoService turnoService;

    @Autowired
    public TurnoController(TurnoService turnoService){
        this.turnoService = turnoService;
    }

    @PutMapping("/assign-caja")
    public ResponseEntity<?> assignCajaToTurn(@RequestBody AssignCajaTurnoDTO assignCajaTurnoDTO) {
        try {
            turnoService.assignCajaToTurn(assignCajaTurnoDTO.getTurnId(), assignCajaTurnoDTO.getCajaId());
            return ResponseEntity.ok("Turno asignado a caja");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<Turn>> getAllTurnos() {
        List<Turn> turnos = turnoService.findAllTurnos();
        return ResponseEntity.ok(turnos);
    }


}
