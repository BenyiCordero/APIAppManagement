package com.managementApp.dto;

import java.util.Date;

public record CorteResponseDTO(
        String nombre,
        String primerApe,
        String segundoApe,
        String email,
        Date fecha,
        Long totalCitas,
        Double totalEfectivo,
        Double totalTarjeta,
        Double totalTransferencia,
        Double totalSalidas
) {
}
