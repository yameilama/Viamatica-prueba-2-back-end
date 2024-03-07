package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.implementation;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.AttentionType;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository.AttentionTypeRepository;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.AttentionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttentionTypeServiceImpl implements AttentionTypeService {

    private final AttentionTypeRepository attentionTypeRepository;

    @Autowired
    public AttentionTypeServiceImpl(AttentionTypeRepository attentionTypeRepository) {
        this.attentionTypeRepository = attentionTypeRepository;
    }
    @Override
    public List<AttentionType> findAll() {
        return attentionTypeRepository.findAll();

    }

    @Override
    public Optional<AttentionType> findByCode(String code) {
        return attentionTypeRepository.findByCode(code);

    }
}
