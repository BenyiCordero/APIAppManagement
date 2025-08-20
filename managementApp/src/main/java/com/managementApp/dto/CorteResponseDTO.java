package com.managementApp.dto;

public record CorteResponseDTO(
        String nombre,
        String primerApe,
        String segundoApe,
        String email,
        Long totalCitas,
        Double totalEfectivo,
        Double totalTarjeta,
        Double totalTransferencia,
        Double totalSalidas
) {
}
