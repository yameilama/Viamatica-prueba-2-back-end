package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.Attention;

import java.util.List;

public interface AttentionService {
    Attention createAttention(Long clientId, Long attentionTypeId);
    List<Attention> getAttentionsForClient(Long clientId);

    List<Attention> findAll();
}
