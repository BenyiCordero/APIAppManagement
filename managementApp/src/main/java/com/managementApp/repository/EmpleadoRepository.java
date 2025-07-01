package com.managementApp.repository;

import com.managementApp.domain.CitaDetails;
import com.managementApp.domain.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    Optional<Empleado> findByEmail(String email);

    @Query("SELECT cd FROM CitaDetails cd WHERE cd.empleado.idEmpleado = :idEmpleado")
    List<CitaDetails> findByEmpleado_Id(Long idEmpleado);
}