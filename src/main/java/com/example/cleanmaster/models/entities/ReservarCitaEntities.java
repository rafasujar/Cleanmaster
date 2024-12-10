package com.example.cleanmaster.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "reservar_cita")
public class ReservarCitaEntities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "id_Cliente", nullable = false)
    private Integer idCliente;

    @Column(name = "id_Empleado", nullable = true)
    private Integer idEmpleado;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "id_Tipo_Servicio", nullable = false)
    private Integer idTipoServicio;

    @Column(name = "id_Direccion", nullable = false, length = 200)
    private Integer idDireccion;

    @Column(name = "especificaciones", length = 200)
    private String especificaciones;

    @Column(name = "finalizada_reserva", nullable = false)
    private Boolean finalizadaReserva = false;

}