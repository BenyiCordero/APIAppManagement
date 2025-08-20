package com.managementApp.service;

import com.managementApp.dto.CorteResponseDTO;
import com.managementApp.repository.CorteRepository;
import org.springframework.stereotype.Service;

@Service
public class CorteServiceImpl implements CorteService {

    private final CorteRepository corteRepository;

    public CorteServiceImpl(CorteRepository corteRepository) {
        this.corteRepository = corteRepository;
    }

    @Override
    public CorteResponseDTO findCorte(String email) {
        return corteRepository.findCorte(email);
    }
}
