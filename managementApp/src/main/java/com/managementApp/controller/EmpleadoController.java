package com.managementApp.controller;

import com.managementApp.domain.Empleado;
import com.managementApp.dto.EmployeeResponse;
import com.managementApp.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmpleadoController {

    private final EmpleadoService service;

    public EmpleadoController(EmpleadoService service) {
        this.service = service;
    }

    @GetMapping("/getEmployee/{email}")
    public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable String email) {
        return service.findByEmailResponse(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        return service.findAll()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getConfiguration/{email}")
    public ResponseEntity<?>  getConfiguration(@PathVariable String email) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findConfiguration(email));
    }

}
