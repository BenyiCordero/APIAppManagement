package com.managementApp.repository;

import com.managementApp.domain.Direccion;
import com.managementApp.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    Optional<Persona> findByNombreAndPrimerApeAndSegundoApe(
            String nombre,
            String primerApe,
            String segundoApe
    );
}
