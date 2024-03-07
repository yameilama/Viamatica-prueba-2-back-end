package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.DTO.UserDTO;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    void register(UserDTO userDTO);
}
