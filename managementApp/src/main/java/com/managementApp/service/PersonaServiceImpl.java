package com.managementApp.service;

import com.managementApp.domain.Persona;
import com.managementApp.exception.TelefonoRepetidoException;
import com.managementApp.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {
    @Autowired
    PersonaRepository repository;

    @Override
    public Persona saveIfNotExists(Persona persona){
        Optional<Persona> personaExists = repository.findByTelefono(persona.getTelefono());

        if (personaExists.isPresent()) {
           throw new TelefonoRepetidoException();
        }
        return repository.save(persona);
    }
}
