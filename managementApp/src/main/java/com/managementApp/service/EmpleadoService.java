package com.managementApp.service;

import com.managementApp.domain.Empleado;
import com.managementApp.dto.EmployeeResponse;

import java.util.Optional;

public interface EmpleadoService {
    Optional<Empleado> findByEmail(String email);
    Optional<EmployeeResponse> findByEmailResponse(String email);
}
