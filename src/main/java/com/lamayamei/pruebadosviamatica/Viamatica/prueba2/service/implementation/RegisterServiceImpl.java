package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.implementation;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.DTO.RegisterDTO;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.Client;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.Rol;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository.ClientRepository;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository.RolRepository;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final ClientRepository clientRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final RolRepository rolRepository;

    @Autowired
    public RegisterServiceImpl(ClientRepository clientRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RolRepository rolRepository){
        this.clientRepository = clientRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.rolRepository = rolRepository;
    }

    @Override
    public void register(RegisterDTO registerDTO) {
        Client client = new Client();
        client.setName(registerDTO.getName());
        client.setLastName(registerDTO.getLastName());
        client.setIdentification(registerDTO.getIdentification());
        client.setPhoneNumber(registerDTO.getPhoneNumber());
        client.setEmail(registerDTO.getEmail());
        client.setUsername(registerDTO.getUsername());
        client.setAddress(registerDTO.getAddress());
        client.setAddressReference(registerDTO.getAddressReference());

        String hashedPassword = bCryptPasswordEncoder.encode(registerDTO.getPassword());
        client.setPassword(hashedPassword);

        Optional<Rol> clientRol = rolRepository.findByRolName("CLIENTE");
        clientRol.ifPresent(client::setRol);
        clientRepository.save(client);

    }
}
