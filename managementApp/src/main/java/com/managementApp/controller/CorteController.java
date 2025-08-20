package com.managementApp.controller;

import com.managementApp.service.CorteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/corte")
public class CorteController {

    private final CorteService corteService;

    public CorteController(CorteService corteService) {
        this.corteService = corteService;
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getCorte(@PathVariable String email) {
        return ResponseEntity.status(HttpStatus.OK).body(corteService.findCorte(email));
    }

}
