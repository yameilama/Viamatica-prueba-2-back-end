package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.Turn;

import java.util.List;

public interface TurnoService {
    void assignCajaToTurn(Long turnId, Long cajaId);

    List<Turn> findAllTurnos();
}
