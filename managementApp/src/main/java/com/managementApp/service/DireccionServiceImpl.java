package com.managementApp.service;

import com.managementApp.domain.Direccion;
import com.managementApp.repository.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DireccionServiceImpl implements DireccionService{

    @Autowired
    DireccionRepository repository;

    @Override
    public Direccion saveIfNotExists(Direccion direccion){
        Optional<Direccion> adressExists = repository.findByCalleAndColoniaAndNumeroExteriorAndNumeroInteriorAndCodigoPostal(direccion.getCalle(), direccion.getColonia(), direccion.getNumeroExterior(), direccion.getNumeroInterior(), direccion.getCodigoPostal());

        if (adressExists.isPresent()) {
            return adressExists.get();
        }

        return repository.save(direccion);
    }
}
