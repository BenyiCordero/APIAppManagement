package com.managementApp.dto;

public record DireccionRequest(
        String calle,
        String colonia,
        String numeroExterior,
        String numeroInterior,
        String codigoPostal
) {
}
