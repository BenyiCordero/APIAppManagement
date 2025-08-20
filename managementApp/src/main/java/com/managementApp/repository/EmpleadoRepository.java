package com.managementApp.repository;

import com.managementApp.domain.Empleado;
import com.managementApp.dto.ConfiguracionProjectionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    Optional<Empleado> findByEmail(String email);
    @Query(value = "SELECT * FROM v_configuracion WHERE email = ?", nativeQuery = true)
    ConfiguracionProjectionResponse findConfiguration(String email);
}