package com.example.cleanmaster.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "empleado_tiposServicios")
public class Empleado_tiposServiciosEntities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "idEmpleado", nullable = false)
    private Integer idEmpleado;

    @Column(name = "idTipoServicio", nullable = false)
    private Integer idTipoServicio;

}