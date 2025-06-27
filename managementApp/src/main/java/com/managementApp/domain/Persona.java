package com.managementApp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Persona")
public class Persona {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Long idPersona;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "primer_ape")
    private String primerApe;
    @Column(name = "segundo_ape")
    private String segundoApe;
    @Column (name = "telefono")
    private String telefono;
    @ManyToOne
    @JoinColumn (name = "id_direccion_fk")
    private Direccion direccion;

}
