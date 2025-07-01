package com.managementApp.controller;

import com.managementApp.domain.CitaDetails;
import com.managementApp.dto.CitaDetailsDTO;
import com.managementApp.service.CitaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/appointment")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @GetMapping ("/getAppointments")
    public ResponseEntity<?> getAllAppointments() {
        List<CitaDetails> citas = citaService.findAllCitas();
        if (citas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(citas);
    }

    @DeleteMapping ("/deleteAppointment/{id}")
    public ResponseEntity<?> deleteAppointments(@PathVariable Long id) {
        try {
            citaService.deleteCita(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping ("/addAppointment")
    public ResponseEntity<?> addAppointment(@RequestBody CitaDetailsDTO citaDetailsDTO) {
        try {
            citaService.saveCita(citaDetailsDTO);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
