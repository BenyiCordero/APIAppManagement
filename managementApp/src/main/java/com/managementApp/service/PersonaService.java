package com.managementApp.service;

import com.managementApp.domain.Direccion;
import com.managementApp.domain.Persona;


public interface PersonaService {
    Persona saveIfNotExists(Persona persona);
}
