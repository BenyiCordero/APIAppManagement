package com.managementApp.service;

import com.managementApp.domain.Persona;
import com.managementApp.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class PersonaServiceImpl implements PersonaService {
    @Autowired
    PersonaRepository repository;

    @Override
    public Persona saveIfNotExists(Persona persona){
        Optional<Persona> personaExists = repository.findByNombreAndPrimerApeAndSegundoApe(persona.getNombre(), persona.getPrimerApe(), persona.getSegundoApe());

        if (personaExists.isPresent()) {
            return personaExists.get();
        }
        return repository.save(persona);
    }
}
