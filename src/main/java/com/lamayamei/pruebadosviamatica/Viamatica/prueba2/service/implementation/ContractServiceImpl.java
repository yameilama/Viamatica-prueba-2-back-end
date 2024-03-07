package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.implementation;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.Client;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.Contract;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository.ClientRepository;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository.ContractRepository;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ContractServiceImpl implements ContractService {
    private final ClientRepository clientRepository;
    private final ContractRepository contractRepository;

    @Autowired
    public ContractServiceImpl(ClientRepository clientRepository, ContractRepository contractRepository) {
        this.clientRepository = clientRepository;
        this.contractRepository = contractRepository;
    }
    @Override
    public List<Contract> findContractByClientUsername(String username) {
        Optional<Client> client = clientRepository.findByUsername(username);
        return client.map(contractRepository::findByClient).orElse(Collections.emptyList());
    }
}
