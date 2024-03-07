package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.implementation;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.DTO.UserDTO;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.Rol;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.User;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository.RolRepository;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository.UserRepository;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RolRepository rolRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,RolRepository rolRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void register(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        String hashedPassword = bCryptPasswordEncoder.encode(userDTO.getPassword());
        user.setPassword(hashedPassword);

        Rol role = rolRepository.findById(userDTO.getRolId()).orElseThrow(()-> new RuntimeException(("Error: no se encontro el rol")));
        user.setRol(role);

        userRepository.save(user);

    }
}
