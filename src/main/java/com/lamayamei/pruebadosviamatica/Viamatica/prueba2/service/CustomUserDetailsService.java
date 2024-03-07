package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.Client;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.entity.User;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository.ClientRepository;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, ClientRepository clientRepository) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Buscando usuario con nombre de usuario: " + username);

        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            return buildUserDetails(optionalUser.get());
        }

        Optional<Client> optionalClient = clientRepository.findByUsername(username);
        if (optionalClient.isPresent()) {
            return buildUserDetails(optionalClient.get());
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }


    private UserDetails buildUserDetails(Object account) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        String username;
        String password;
        String roleName;

        if (account instanceof User) {
            User user = (User) account;
            username = user.getUsername();
            password = user.getPassword();
            roleName = user.getRol().getRolName();
        } else if (account instanceof Client) {
            Client client = (Client) account;
            username = client.getUsername();
            password = client.getPassword();
            roleName = client.getRol().getRolName();
        } else {
            throw new IllegalArgumentException("Error");
        }

        if (roleName != null) {
            authorities.add(new SimpleGrantedAuthority(roleName));
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(password)
                .authorities(authorities)
                .build();
    }
}
