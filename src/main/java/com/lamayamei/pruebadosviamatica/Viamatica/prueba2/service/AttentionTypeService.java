package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.AttentionType;

import java.util.List;
import java.util.Optional;

public interface AttentionTypeService {
    List<AttentionType> findAll();
    Optional<AttentionType> findByCode(String code);

}
