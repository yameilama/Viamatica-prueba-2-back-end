package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.implementation;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.Attention;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.AttentionStatus;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.AttentionType;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.Client;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository.AttentionRepository;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository.AttentionStatusRepository;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository.AttentionTypeRepository;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository.ClientRepository;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.AttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttentionServiceImpl implements AttentionService {

    private final AttentionRepository attentionRepository;
    private final ClientRepository clientRepository;
    private final AttentionStatusRepository attentionStatusRepository;
    private final AttentionTypeRepository attentionTypeRepository;


    @Autowired
    public AttentionServiceImpl(AttentionRepository attentionRepository,
                                ClientRepository clientRepository,
                                AttentionStatusRepository attentionStatusRepository,
                                AttentionTypeRepository attentionTypeRepository
                                ) {
        this.attentionRepository = attentionRepository;
        this.clientRepository = clientRepository;
        this.attentionStatusRepository = attentionStatusRepository;
        this.attentionTypeRepository = attentionTypeRepository;
    }

    @Override
    public Attention createAttention(Long clientId, Long attentionTypeId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        AttentionType attentionType = attentionTypeRepository.findById(attentionTypeId)
                .orElseThrow(() -> new RuntimeException("Attention type no encontrado"));

        AttentionStatus status = attentionStatusRepository.findByCode("A01")
                .orElseThrow(() -> new RuntimeException("Status no encontrado"));

        Attention attention = new Attention();
        attention.setClient(client);
        attention.setAttentionType(attentionType);
        attention.setAttentionStatus(status);

        return attentionRepository.save(attention);
    }

    @Override
    public List<Attention> getAttentionsForClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return attentionRepository.findByClient(client);
    }
}
