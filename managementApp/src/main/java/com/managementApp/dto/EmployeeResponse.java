package com.managementApp.dto;

import com.managementApp.auth.Rol;
import com.managementApp.domain.Persona;

public record EmployeeResponse(
        Long id,
        Rol rol,
        Persona persona
) {
}
