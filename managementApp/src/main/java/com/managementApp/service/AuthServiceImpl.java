package com.managementApp.service;

import com.managementApp.auth.Rol;
import com.managementApp.domain.Direccion;
import com.managementApp.domain.Empleado;
import com.managementApp.domain.Persona;
import com.managementApp.domain.Token;
import com.managementApp.dto.AuthRequest;
import com.managementApp.dto.RegisterRequest;
import com.managementApp.dto.TokenResponse;
import com.managementApp.repository.DireccionRepository;
import com.managementApp.repository.EmpleadoRepository;
import com.managementApp.repository.PersonaRepository;
import com.managementApp.repository.TokenRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    EmpleadoRepository repository;
    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    EmpleadoService empleadoService;
    @Autowired
    DireccionService direccionService;
    @Autowired
    PersonaRepository personaRepository;

    @Transactional //Hace que se haga todo y si falla no se hace nada, a falta de manejar la excepcion
    @Override
    public TokenResponse register(final RegisterRequest request) {
        Direccion direccion = Direccion.builder()
                .calle(request.calle())
                .colonia(request.colonia())
                .numeroExterior(request.numeroExterior())
                .numeroInterior(request.numeroInterior())
                .codigoPostal(request.codigoPostal())
                .build();
        Direccion savedDireccion = direccionService.saveIfNotExists(direccion);

        Persona persona = Persona.builder()
                .nombre(request.name())
                .primerApe(request.primerApellido())
                .segundoApe(request.segundoApellido())
                .telefono(request.telefono())
                .direccion(savedDireccion)
                .build();
        Persona savedPersona = personaRepository.save(persona);

        Empleado empleado = Empleado.builder()
                .persona(savedPersona)
                .rol(Rol.ADMIN)
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();
        final Empleado savedAdminUser = repository.save(empleado);

        final String jwtToken = jwtService.generateToken(savedAdminUser);
        final String refreshToken = jwtService.generateRefreshToken(savedAdminUser);

        saveUserToken(savedAdminUser, jwtToken);
        return new TokenResponse(jwtToken, refreshToken);
    }

    @Override
    public TokenResponse authenticate(final AuthRequest request) {
        System.out.println("Attempting login with email: " + request.email() + ", password: " + request.password());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );
        } catch (AuthenticationException e) {
            System.out.println("Authentication failed:");
            e.printStackTrace();
            throw e;
        }
        final Empleado adminUser = empleadoService.findByEmail(request.email())
                .orElseThrow();
        final String accessToken = jwtService.generateToken(adminUser);
        final String refreshToken = jwtService.generateRefreshToken(adminUser);
        revokeAllUserTokens(adminUser);
        saveUserToken(adminUser, accessToken);
        return new TokenResponse(accessToken, refreshToken);
    }

    @Override
    public void saveUserToken(Empleado empleado, String jwtToken) {
        final Token token = Token.builder()
                .empleado(empleado)
                .token(jwtToken)
                .tokenType(Token.TokenType.BEARER)
                .isExpired(false)
                .isRevoked(false)
                .build();
        tokenRepository.save(token);
    }

    @Override
    @Transactional // Asegúrate de que este método o el que lo llama tenga @Transactional
    public void revokeAllUserTokens(final Empleado adminUser) {
        // Encuentra TODOS los tokens de ese usuario, activos o inactivos.
        // Esto es más seguro para evitar duplicados si un token "viejo" que no era "valid" causó el problema.
        final List<Token> allUserTokens = tokenRepository.findByEmpleado(adminUser); // Necesitarías este método en tu TokenRepository

        if (!allUserTokens.isEmpty()) {
            tokenRepository.deleteAll(allUserTokens); // <-- Elimina todos los tokens anteriores del usuario
        }
    }
    @Override
    public TokenResponse refreshToken(@NotNull final String authentication) {

        if (authentication == null || !authentication.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid auth header");
        }
        final String refreshToken = authentication.substring(7);
        final String userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail == null) {
            return null;
        }

        final Empleado adminUser = this.empleadoService.findByEmail(userEmail).orElseThrow();
        final boolean isTokenValid = jwtService.isTokenValid(refreshToken, adminUser);
        if (!isTokenValid) {
            return null;
        }

        final String accessToken = jwtService.generateRefreshToken(adminUser);
        revokeAllUserTokens(adminUser);
        saveUserToken(adminUser, accessToken);

        return new TokenResponse(accessToken, refreshToken);
    }
}
