package com.managementApp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "Direccion")
public class Direccion {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_direccion")
    private Long idDireccion;
    @Column (name = "calle")
    private String calle;
    @Column (name = "colonia")
    private String colonia;
    @Column (name = "numero_exterior")
    private String numeroExterior;
    @Column (name = "numero_interior")
    private String numeroInterior;
    @Column (name = "codigo_postal")
    private String codigoPostal;

}
