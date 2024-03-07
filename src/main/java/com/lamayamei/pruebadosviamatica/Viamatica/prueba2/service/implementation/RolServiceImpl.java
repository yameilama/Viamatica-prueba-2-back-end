package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.implementation;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.Rol;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository.RolRepository;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    @Autowired
    public  RolServiceImpl (RolRepository rolRepository){
        this.rolRepository = rolRepository;
    }

    @Override
    public List<Rol> findAllRoles() {
        return rolRepository.findAll();
    }
}
