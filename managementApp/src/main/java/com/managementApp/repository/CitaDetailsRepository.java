package com.managementApp.repository;

import com.managementApp.domain.CitaDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaDetailsRepository extends JpaRepository<CitaDetails, Long> {
}
