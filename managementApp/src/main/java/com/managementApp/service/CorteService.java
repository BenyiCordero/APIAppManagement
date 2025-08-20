package com.managementApp.service;

import com.managementApp.dto.CorteResponseDTO;

public interface CorteService {
    CorteResponseDTO findCorte(String email);
}
