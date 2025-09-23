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
@Table(name = "Corte")
public class Corte {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_corte;

    @Column(nullable = false)
    private String Nombre;

    @Column(nullable = false)
    private String primerApe;

    @Column(nullable = false)
    private String segundoApe;

    @Column(nullable = false)
    private Long totalCitas;

    @Column(nullable = false)
    private  Double totalEfectivo;

    @Column(nullable = false)
    private Double totalTarjeta;

    @Column(nullable = false)
    private Double totalTransferencia;

    @Column(nullable = false)
    private Double totalSalida;

    @Column(nullable = false)
    private Double subtotal;

    @Column(nullable = false)
    private Date fecha;


    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "id_salida")
    private SalidaEfectivo salidaEfectivo;

}
