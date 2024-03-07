package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.service;

import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.DTO.LoginDTO;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.config.JwtTokenProvider;
import org.springframework.security.core.GrantedAuthority;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.repository.UserRepository;
import com.lamayamei.pruebadosviamatica.Viamatica.prueba2.response.JwtAuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final CustomUserDetailsService userDetailsService;

    private final UserRepository userRepository;

    @Autowired
    public LoginService(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider,
                        CustomUserDetailsService userDetailsService,
                        UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }

    public JwtAuthenticationResponse authenticate(LoginDTO loginDTO) throws RuntimeException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        String jwt = tokenProvider.generateToken(authentication);

        return new JwtAuthenticationResponse(jwt, roles, userDetails.getUsername());
    }


}
