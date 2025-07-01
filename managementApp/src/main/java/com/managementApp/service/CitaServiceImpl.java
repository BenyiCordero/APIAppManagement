package com.managementApp.service;

import com.managementApp.domain.Cita;
import com.managementApp.domain.CitaDetails;
import com.managementApp.domain.Cliente;
import com.managementApp.domain.Empleado;
import com.managementApp.dto.CitaDetailsDTO;
import com.managementApp.repository.CitaDetailsRepository;
import com.managementApp.repository.CitaRepository;
import com.managementApp.repository.ClienteRepository;
import com.managementApp.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaServiceImpl implements CitaService {


    private final CitaRepository citaRepository;
    private final CitaDetailsRepository citaDetailsRepository;
    private final EmpleadoRepository empleadoRepository;
    private final ClienteRepository clienteRepository;

    public CitaServiceImpl(CitaRepository citaRepository, CitaDetailsRepository citaDetailsRepository, EmpleadoRepository empleadoRepository, ClienteRepository clienteRepository) {
        this.citaRepository = citaRepository;
        this.citaDetailsRepository = citaDetailsRepository;
        this.empleadoRepository = empleadoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public CitaDetails saveCita(CitaDetailsDTO citaDetailsDTO) {
        Cita cita = new Cita();
        cita.setDescripcion(citaDetailsDTO.descripcion());
        Cita citaGuardada = citaRepository.save(cita);

        Empleado empleado = empleadoRepository.findById(citaDetailsDTO.id_empleado())
                .orElseThrow(() -> new RuntimeException("No existe el empleado"));

        Cliente cliente = clienteRepository.findById(citaDetailsDTO.id_cliente())
                .orElseThrow(() -> new RuntimeException("No existe el cliente"));

        CitaDetails citaDetails = new CitaDetails();
        citaDetails.setMonto(citaDetailsDTO.monto());
        citaDetails.setHoraInicio(citaDetailsDTO.horaInicio());
        citaDetails.setHoraFin(citaDetailsDTO.horaFin());
        citaDetails.setFecha(citaDetailsDTO.fecha());
        citaDetails.setCita(citaGuardada);
        citaDetails.setEmpleado(empleado);
        citaDetails.setCliente(cliente);

        return citaDetailsRepository.save(citaDetails);
    }

    @Override
    public void deleteCita(Long id) {
        try {
            citaDetailsRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCita(CitaDetailsDTO citaDetailsDTO) {

    }

    @Override
    public List<CitaDetails> findAllCitas() {
        return citaDetailsRepository.findAll();
    }

    @Override
    public Optional<Cita> findCitaById(Long id) {
        Optional<Cita> cita = citaRepository.findById(id);
        return cita;
    }

    //**Vamos a corregir el delete **
}
