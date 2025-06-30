package com.managementApp.repository;

import com.managementApp.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Cliente, Long> {

}
