package com.managementApp.controller;

import com.managementApp.domain.Empleado;
import com.managementApp.service.EmpleadoService;
import com.managementApp.service.SalidaEfectivoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/salida")
public class SalidaController {

    private final SalidaEfectivoService salidaEfectivoService;
    private final EmpleadoService empleadoService;

    public SalidaController(SalidaEfectivoService salidaEfectivoService, EmpleadoService empleadoService) {
        this.salidaEfectivoService = salidaEfectivoService;
        this.empleadoService = empleadoService;
    }

    @GetMapping("/{empleadoId}")
    public ResponseEntity<?> getSalida(@PathVariable Long empleadoId) {
        Empleado empleado = empleadoService.findById(empleadoId).get();

        return ResponseEntity.status(HttpStatus.OK).body(salidaEfectivoService.findByEmpleado(empleado));
    }

}
