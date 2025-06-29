package com.managementApp.service;

import com.managementApp.domain.Empleado;
import com.managementApp.dto.AuthRequest;
import com.managementApp.dto.RegisterRequest;
import com.managementApp.dto.TokenResponse;
import jakarta.validation.constraints.NotNull;

public interface AuthService {
    TokenResponse register(final RegisterRequest request);
    TokenResponse authenticate(final AuthRequest request);
    void saveUserToken(Empleado empleado, String jwtToken);
    void revokeAllUserTokens(final Empleado empleado);
    TokenResponse refreshToken(@NotNull final String authentication);
}
