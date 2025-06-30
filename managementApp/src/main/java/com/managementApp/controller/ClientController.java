package com.managementApp.controller;

import com.managementApp.domain.Cliente;
import com.managementApp.dto.ClientRequest;
import com.managementApp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClients() {
        return service.findAll()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Object> postClient(@RequestBody ClientRequest cliente){
        final Cliente response = service.postClient(cliente);
        return ResponseEntity.status(204).build();
    }
}
