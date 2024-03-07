package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.Contract;

import java.util.List;

public interface ContractService {
    List<Contract> findContractByClientUsername(String identification);

}
