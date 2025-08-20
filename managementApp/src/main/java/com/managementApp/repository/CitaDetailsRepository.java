package com.managementApp.repository;

import com.managementApp.domain.CitaDetails;
import com.managementApp.dto.TotalCitasResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaDetailsRepository extends JpaRepository<CitaDetails, Long> {
    @Query("SELECT cd FROM CitaDetails cd WHERE cd.empleado.idEmpleado = :idEmpleado")
    List<CitaDetails> findByEmpleado_Id(Long idEmpleado);
    @Query(value = "SELECT fecha_hoy, total_citas FROM v_total_citas", nativeQuery = true )
    TotalCitasResponse CitaDetails();
    @Query(value = "SELECT * FROM v_total_hoy", nativeQuery = true)
    Double calcularTotalCitas();
}
