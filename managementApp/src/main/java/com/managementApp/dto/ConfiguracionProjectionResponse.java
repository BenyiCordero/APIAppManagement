package com.managementApp.dto;

public record ConfiguracionProjectionResponse(
        String rol,
        String email,
        String nombre,
        String primerApe,
        String segundoApe,
        String telefono,
        String calle,
        String colonia,
        String codigoPostal,
        String numeroExterior,
        String numeroInterior
) {
}
