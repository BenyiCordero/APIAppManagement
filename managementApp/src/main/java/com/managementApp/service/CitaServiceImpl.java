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
import org.springframework.transaction.annotation.Transactional; // Para transacciones
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
    @Transactional
    public CitaDetails saveCita(CitaDetailsDTO citaDetailsDTO) {
        if (citaDetailsDTO.id_empleado() == null) {
            throw new RuntimeException("El ID del empleado no puede ser nulo.");
        }
        if (citaDetailsDTO.id_cliente() == null) {
            throw new RuntimeException("El ID del cliente no puede ser nulo.");
        }

        Cita cita = new Cita();
        cita.setDescripcion(citaDetailsDTO.descripcion());
        Cita citaGuardada = citaRepository.save(cita);

        Empleado empleado = empleadoRepository.findById(citaDetailsDTO.id_empleado())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con ID: " + citaDetailsDTO.id_empleado()));

        Cliente cliente = clienteRepository.findById(citaDetailsDTO.id_cliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + citaDetailsDTO.id_cliente()));

        CitaDetails citaDetails = new CitaDetails();
        citaDetails.setMonto(citaDetailsDTO.monto());

        List<CitaDetails>  citas = citaDetailsRepository.findByEmpleado_Id(empleado.getIdEmpleado());

        for (CitaDetails citaDetail : citas) {
            if (citaDetailsDTO.horaInicio().equals(citaDetail.getHoraInicio())){
                throw new RuntimeException("Hora repetida");
            }
        }
        citaDetails.setHoraInicio(citaDetailsDTO.horaInicio());
        citaDetails.setHoraFin(citaDetailsDTO.horaFin());
        citaDetails.setFecha(citaDetailsDTO.fecha());
        citaDetails.setCita(citaGuardada);
        citaDetails.setEmpleado(empleado);
        citaDetails.setCliente(cliente);

        return citaDetailsRepository.save(citaDetails);
    }

    @Override
    @Transactional
    public void deleteCita(Long id, CitaDetailsDTO citaDetailsDTO) {

        Empleado empleado = empleadoRepository.findById(citaDetailsDTO.id_empleado())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con ID: " + citaDetailsDTO.id_empleado()));

        List<CitaDetails> citas = citaDetailsRepository.findByEmpleado_Id(empleado.getIdEmpleado());
        for (CitaDetails citaDetails : citas) {
            if (citaDetails.getId_citad().equals(id)) {
                citaDetailsRepository.delete(citaDetails);
            }
        }
    }

    @Override
    @Transactional
    public CitaDetails updateCita(Long id, CitaDetailsDTO citaDetailsDTO) {
        CitaDetails citaDetailsExistente = citaDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita (detalles) no encontrada con ID: " + id));

        Cita citaExistente = citaDetailsExistente.getCita();
        if (citaExistente == null) {
            throw new RuntimeException("La Cita asociada a los detalles de cita con ID " + id + " no fue encontrada.");
        }
        citaExistente.setDescripcion(citaDetailsDTO.descripcion());

        Empleado empleado = empleadoRepository.findById(citaDetailsDTO.id_empleado())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con ID: " + citaDetailsDTO.id_empleado()));

        Cliente cliente = clienteRepository.findById(citaDetailsDTO.id_cliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + citaDetailsDTO.id_cliente()));

        citaDetailsExistente.setMonto(citaDetailsDTO.monto());
        citaDetailsExistente.setHoraInicio(citaDetailsDTO.horaInicio());
        citaDetailsExistente.setHoraFin(citaDetailsDTO.horaFin());
        citaDetailsExistente.setFecha(citaDetailsDTO.fecha());
        citaDetailsExistente.setEmpleado(empleado);
        citaDetailsExistente.setCliente(cliente);

        return citaDetailsRepository.save(citaDetailsExistente);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CitaDetails> findAllCitas(Long id) {
        return citaDetailsRepository.findByEmpleado_Id(id);
    }
}