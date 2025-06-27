package com.managementApp.service;

import com.managementApp.domain.Direccion;
import com.managementApp.repository.DireccionRepository;
import org.springframework.stereotype.Service;

@Service
public class DireccionServiceImpl implements DireccionService{

    private final DireccionRepository direccionRepository;

    public DireccionServiceImpl(DireccionRepository direccionRepository) {
        this.direccionRepository = direccionRepository;
    }

    @Override
    public void crearDireccion(Direccion direccion) {
        direccionRepository.save(direccion);
    }
}
