package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.implementation;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.*;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository.*;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.AttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AttentionServiceImpl implements AttentionService {

    private final AttentionRepository attentionRepository;
    private final ClientRepository clientRepository;
    private final AttentionStatusRepository attentionStatusRepository;
    private final AttentionTypeRepository attentionTypeRepository;
    private final TurnRepository turnRepository;


    @Autowired
    public AttentionServiceImpl(AttentionRepository attentionRepository,
                                ClientRepository clientRepository,
                                AttentionStatusRepository attentionStatusRepository,
                                AttentionTypeRepository attentionTypeRepository,
                                TurnRepository turnRepository
                                ) {
        this.attentionRepository = attentionRepository;
        this.clientRepository = clientRepository;
        this.attentionStatusRepository = attentionStatusRepository;
        this.attentionTypeRepository = attentionTypeRepository;
        this.turnRepository = turnRepository;
    }

    @Override
    public Attention createAttention(Long clientId, Long attentionTypeId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        AttentionType attentionType = attentionTypeRepository.findById(attentionTypeId)
                .orElseThrow(() -> new RuntimeException("Attention type no encontrado"));

        AttentionStatus status = attentionStatusRepository.findByCode("A01")
                .orElseThrow(() -> new RuntimeException("Status no encontrado")); //status generado

        Attention attention = new Attention();
        attention.setClient(client);
        attention.setAttentionType(attentionType);
        attention.setAttentionStatus(status);
        Attention savedAttention = attentionRepository.save(attention);

        String turnDescription = generateTurnDescription(attentionType.getDescription(), savedAttention.getAttentionId());
        Turn turn = new Turn();
        turn.setDescription(turnDescription);
        turn.setDate(new Date());
        turn.setClient(client);
      //  return attentionRepository.save(attention);

        Turn savedTurn = turnRepository.save(turn);
        savedAttention.setTurn(savedTurn);
        attentionRepository.save(savedAttention);

        return savedAttention;
    }

    private String generateTurnDescription(String attentionTypeDesc, Long attentionId) {
        String numberPart = String.format("%04d", attentionId % 10000); // 4 digitos

        String letterPart = attentionTypeDesc.substring(0, 2).toUpperCase();

        return letterPart + numberPart;
    }

    @Override
    public List<Attention> getAttentionsForClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return attentionRepository.findByClient(client);
    }

    @Override
    public List<Attention> findAll() {
        return attentionRepository.findAll();
    }
}
