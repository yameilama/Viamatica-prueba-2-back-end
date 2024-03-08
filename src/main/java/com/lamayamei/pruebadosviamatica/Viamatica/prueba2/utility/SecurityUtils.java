package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.utility;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("No se encontró usuario autenticado");
        }
        return authentication.getName();
    }
}
