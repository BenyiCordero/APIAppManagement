package com.managementApp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SalidaEfectivo")
public class SalidaEfectivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_salida;

    @Column(nullable = false, length = 80)
    private String descripcion;

    @Column(nullable = false)
    private Double monto;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado empleado;
}
