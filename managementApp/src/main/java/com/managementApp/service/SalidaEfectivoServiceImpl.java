package com.managementApp.service;

import com.managementApp.domain.Empleado;
import com.managementApp.domain.SalidaEfectivo;
import com.managementApp.repository.SalidaEfectivoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalidaEfectivoServiceImpl implements SalidaEfectivoService {

    private final SalidaEfectivoRepository salidaEfectivoRepository;

    public SalidaEfectivoServiceImpl(SalidaEfectivoRepository salidaEfectivoRepository) {
        this.salidaEfectivoRepository = salidaEfectivoRepository;
    }

    @Override
    public List<SalidaEfectivo> findByEmpleado(Empleado empleado) {
        return salidaEfectivoRepository.findByEmpleado(empleado);
    }
}
