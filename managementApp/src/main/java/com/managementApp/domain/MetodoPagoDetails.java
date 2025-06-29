package com.managementApp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MetodoPagoDetails")
public class MetodoPagoDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_metodod;

    @Column(nullable = false)
    private Double monto;

    @ManyToOne
    @JoinColumn(name = "id_metodo", nullable = false)
    private MetodoPago metodoPago;

    @ManyToOne
    @JoinColumn(name = "id_cita", nullable = false)
    private Cita cita;
}
