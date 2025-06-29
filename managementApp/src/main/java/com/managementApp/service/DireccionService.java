package com.managementApp.service;

import com.managementApp.domain.Direccion;
import org.springframework.beans.factory.annotation.Autowired;

public interface DireccionService {
    Direccion saveIfNotExists(Direccion direccion);
}
