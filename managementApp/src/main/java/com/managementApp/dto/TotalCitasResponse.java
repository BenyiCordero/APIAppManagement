package com.managementApp.dto;

import java.util.Date;

public record TotalCitasResponse(
        Date fechaHoy,
        Long totalCitas
) {
}
