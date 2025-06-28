package com.managementApp.domain;

import com.managementApp.auth.Rol;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "Empleado")
@Builder
public class Empleado {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_empleado")
    private Long idEmpleado;
    @Enumerated(EnumType.STRING)
    @Column (name = "rol")
    private Rol rol;
    @OneToOne
    @JoinColumn (name = "id_persona_fk")
    private Persona persona;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

}
