package com.managementApp.service;

import com.managementApp.domain.Empleado;

import javax.crypto.SecretKey;
import java.util.Date;

public interface JwtService {
    String extractUsername(String token);

    String generateToken(final Empleado empleado); // Cambiado a UserDetails

    String generateRefreshToken(final Empleado empleado); // Cambiado a UserDetails

    String buildToken(final Empleado empleado, final long expiration); // Cambiado a UserDetails

    boolean isTokenValid(String token, Empleado empleado); // Cambiado a UserDetails

    boolean isTokenExpired(String token);

    Date extractExpiration(String token);

    SecretKey getSignInKey();

}
