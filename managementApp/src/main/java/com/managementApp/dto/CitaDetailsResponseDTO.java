package com.managementApp.dto;

import java.sql.Time;
import java.util.Date;

public record CitaDetailsResponseDTO(
        Date fecha,
        Time horaInicio,
        Time horaFin,
        Double monto,
        Long idEmpleado,
        String nombre,
        String primerApe,
        String descripcion
) {
}
