package com.managementApp.service;

import com.managementApp.domain.Empleado;

import java.util.Optional;

public interface EmpleadoService {
    Optional<Empleado> findByEmail(String email);
}
