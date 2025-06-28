package com.managementApp.service;

import com.managementApp.domain.Empleado;
import com.managementApp.repository.EmpleadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
}
