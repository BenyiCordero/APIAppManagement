package com.managementApp.dto;

import com.managementApp.domain.Persona;
import jakarta.persistence.Column;

public record ClientRequest(
        String nombre,
        String primerApe,
        String segundoApe,
        String telefono,
        String calle,
        String colonia,
        String numeroExterior,
        String numeroInterior,
        String codigoPostal
        ) {
}
