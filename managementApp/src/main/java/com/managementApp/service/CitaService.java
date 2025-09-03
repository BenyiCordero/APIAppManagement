package com.managementApp.service;

import com.managementApp.domain.CitaDetails;
import com.managementApp.dto.CitaDetailsDTO;
import com.managementApp.dto.CitaDetailsResponseDTO;
import com.managementApp.dto.TotalCitasResponse;

import java.util.List;

public interface CitaService {

    CitaDetails saveCita (CitaDetailsDTO citaDetailsDTO);
    void deleteCita (Long id, CitaDetailsDTO citaDetailsDTO);
    CitaDetails updateCita (Long id, CitaDetailsDTO citaDetailsDTO);
    List<CitaDetails> findAllCitas(Long id);
    TotalCitasResponse CitaDetails();
    Double calcularTotalCitas();
    List<CitaDetailsResponseDTO> findCitasByEmpleado(Long idEmpleado);

}
