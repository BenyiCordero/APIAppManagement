package com.managementApp.service;

import com.managementApp.domain.Cliente;
import com.managementApp.dto.ClientRequest;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Optional<List<Cliente>> findAll();
    Cliente postClient(ClientRequest cliente);
}
