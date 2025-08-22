package com.managementApp.service;

import com.managementApp.domain.Empleado;
import com.managementApp.domain.SalidaEfectivo;

import java.util.List;

public interface SalidaEfectivoService {

    List<SalidaEfectivo> findByEmpleado(Empleado empleado);

}
