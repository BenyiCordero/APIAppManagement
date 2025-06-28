package com.managementApp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MetodoPago")
public class MetodoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_metodo;

    @Column(nullable = false, length = 20)
    private String nombre;

    @Column(nullable = false, length = 20)
    private String descripcion;
}
