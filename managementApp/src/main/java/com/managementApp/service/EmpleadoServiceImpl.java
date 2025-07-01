package com.managementApp.service;

import com.managementApp.domain.Empleado;
import com.managementApp.dto.EmployeeResponse;
import com.managementApp.exception.EmpleadoExistenteException;
import com.managementApp.repository.EmpleadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements UserDetailsService, EmpleadoService {
    @Autowired
    EmpleadoRepository empleadoRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return findByEmail(email)
                .map(empleado -> org.springframework.security.core.userdetails.User
                        .builder()
                        .username(empleado.getEmail())
                        .password(empleado.getPassword())
                        .authorities("ROLE_" + empleado.getRol().name())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public Optional<Empleado> findByEmail(String email) {
        return empleadoRepository.findByEmail(email);
    }

    @Override
    public Optional<EmployeeResponse> findByEmailResponse(String email) {
        return empleadoRepository.findByEmail(email)
                .map(empleado -> new EmployeeResponse(
                        empleado.getRol(),
                        empleado.getPersona()
                ));
    }

    @Override
    public Optional<List<EmployeeResponse>> findAll() {
        List<Empleado> empleados = empleadoRepository.findAll();
        List<EmployeeResponse> response = new ArrayList<>();

        if (empleados.isEmpty()) {
            return Optional.empty();
        }

        for(Empleado e : empleados) {
            response.add(new EmployeeResponse(e.getRol(), e.getPersona()));
        }
        return Optional.of(response);
    }

    @Override
    public Empleado saveIfNotExists(Empleado empleado) {
        Optional<Empleado> employeeExists = empleadoRepository.findByEmail(empleado.getEmail());

        if(employeeExists.isPresent()) {
            throw new EmpleadoExistenteException();
        }

        return empleadoRepository.save(empleado);
    }
}
