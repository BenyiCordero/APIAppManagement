package com.managementApp.service;

import com.managementApp.domain.Cita;
import com.managementApp.domain.CitaDetails;
import com.managementApp.dto.CitaDetailsDTO;

import java.util.List;
import java.util.Optional;

public interface CitaService {

    CitaDetails saveCita (CitaDetailsDTO citaDetailsDTO);
    void deleteCita (Long id);
    void updateCita (CitaDetailsDTO citaDetailsDTO);
    List<CitaDetails> findAllCitas();
    Optional<Cita> findCitaById (Long id);

}
