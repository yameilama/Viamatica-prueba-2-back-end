package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.implementation;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.Caja;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.Turn;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository.CajaRepository;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository.TurnRepository;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoServiceImpl implements TurnoService {

    private final TurnRepository turnRepository;
    private final CajaRepository cajaRepository;

    @Autowired
    public TurnoServiceImpl(TurnRepository turnRepository, CajaRepository cajaRepository){
        this.turnRepository = turnRepository;
        this.cajaRepository = cajaRepository;
    }

    @Override
    public void assignCajaToTurn(Long turnId, Long cajaId) {
        Turn turn = turnRepository.findById(turnId)
                .orElseThrow(() -> new RuntimeException("Turno no encontrado"));

        Caja caja = cajaRepository.findById(cajaId)
                .orElseThrow(() -> new RuntimeException("Caja no encontrada"));

        turn.setCaja(caja);
        turnRepository.save(turn);
    }

    @Override
    public List<Turn> findAllTurnos() {
        return turnRepository.findAll();
    }
}
