package com.managementApp.dto;

import java.time.LocalDate;
import java.sql.Time;

public record CitaDetailsDTO (
        String descripcion,
        Double monto,
        Time horaInicio,
        Time horaFin,
        LocalDate fecha,
        Long idEmpleado,
        Long idCliente
){
}
