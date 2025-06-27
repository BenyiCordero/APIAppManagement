package com.managementApp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "Cliente")
public class Cliente {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_cliente")
    private Long idCliente;
    @OneToOne
    @JoinColumn(name = "id_persona_fk")
    private Persona persona;

}
