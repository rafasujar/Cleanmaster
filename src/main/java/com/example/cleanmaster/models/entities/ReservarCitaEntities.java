package com.example.cleanmaster.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "reservarCita")
public class ReservarCitaEntities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "idCliente", nullable = false)
    private Integer idCliente;

    @Column(name = "idEmpleado", nullable = false)
    private Integer idEmpleado;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "idTipoServicio", nullable = false)
    private Integer idTipoServicio;

    @Column(name = "direccion", nullable = false, length = 200)
    private String direccion;

    @Column(name = "especificaciones", length = 200)
    private String especificaciones;

}