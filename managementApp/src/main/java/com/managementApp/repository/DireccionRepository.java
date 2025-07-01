package com.managementApp.repository;

import com.managementApp.domain.Direccion;
import com.managementApp.domain.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long> {
    Optional<Direccion> findByCalleAndColoniaAndNumeroExteriorAndNumeroInteriorAndCodigoPostal(
            String calle,
            String colonia,
            String numeroExterior,
            String numeroInterior,
            String codigoPostal
    );
}
