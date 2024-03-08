package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.implementation;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.Caja;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository.CajaRepository;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.CajaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CajaServiceImpl implements CajaService {

    private final CajaRepository cajaRepository;

    @Autowired
    public CajaServiceImpl(CajaRepository cajaRepository){
        this.cajaRepository = cajaRepository;
    }

    @Override
    public List<Caja> findAll() {
        return cajaRepository.findAll();
    }
}
