package com.managementApp.repository;

import com.managementApp.domain.Empleado;
import com.managementApp.domain.SalidaEfectivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalidaEfectivoRepository extends JpaRepository<SalidaEfectivo, Long> {
    List<SalidaEfectivo> findByEmpleado(Empleado empleado);
}
