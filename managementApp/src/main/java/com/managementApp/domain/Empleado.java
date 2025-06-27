package com.managementApp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "Empleado")
public class Empleado {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_empleado")
    private Long idEmpleado;
    @Column (name = "rol")
    private String rol;
    @OneToOne
    @JoinColumn (name = "id_persona_fk")
    private Persona persona;

}
