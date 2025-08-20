package com.managementApp.repository;

import com.managementApp.domain.Corte;
import com.managementApp.dto.CorteResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CorteRepository extends JpaRepository<Corte, Long> {
    @Query(value = "SELECT * FROM v_corte WHERE email = ?", nativeQuery = true)
    CorteResponseDTO findCorte(String email);
}
