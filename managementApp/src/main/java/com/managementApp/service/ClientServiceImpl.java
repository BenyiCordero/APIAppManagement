package com.managementApp.service;

import com.managementApp.domain.Cliente;
import com.managementApp.domain.Direccion;
import com.managementApp.domain.Persona;
import com.managementApp.dto.ClientRequest;
import com.managementApp.repository.ClientRepository;
import com.managementApp.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private DireccionService direccionService;

    @Autowired
    private PersonaService personaService;

    @Override
    public Optional<List<Cliente>> findAll() {
         return Optional.of(repository.findAll());
    }

    @Transactional
    @Override
    public Cliente postClient(ClientRequest request) {
        Direccion direccion = Direccion.builder()
                .calle(request.calle())
                .colonia(request.colonia())
                .numeroExterior(request.numeroExterior())
                .numeroInterior(request.numeroInterior())
                .codigoPostal(request.codigoPostal())
                .build();
        Direccion savedDireccion = direccionService.saveIfNotExists(direccion);

        Persona persona = Persona.builder()
                .nombre(request.nombre())
                .primerApe(request.primerApe())
                .segundoApe(request.segundoApe())
                .telefono(request.telefono())
                .direccion(savedDireccion)
                .build();
        Persona savedPersona = personaService.saveIfNotExists(persona);

        Cliente clienteMapped = Cliente.builder()
                .persona(savedPersona)
                .build();
        final Cliente savedCliente = repository.save(clienteMapped);

        return savedCliente;
    }
}
