package com.managementApp.dto;

import com.managementApp.auth.Rol;

public record RegisterRequest(
        String name,
        String email,
        String password,
        String primerApellido,
        String segundoApellido,
        String telefono,
        Rol rol,
        String calle,
        String colonia,
        String numeroExterior,
        String numeroInterior,
        String codigoPostal
){

}
