package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private String roles;
    private String username;

    public JwtAuthenticationResponse(String accessToken, String roles, String username) {
        this.accessToken = accessToken;
        this.roles = roles;
        this.username = username;
    }
}
