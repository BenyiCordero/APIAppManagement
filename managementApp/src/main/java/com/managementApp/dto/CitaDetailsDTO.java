package com.managementApp.dto;

import java.util.Date;
import java.sql.Time;

public record CitaDetailsDTO (
        String descripcion,
        Double monto,
        Time horaInicio,
        Time horaFin,
        Date fecha,
        Long id_empleado,
        Long id_cliente
){
}
