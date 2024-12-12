package com.example.cleanmaster.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "empleado_tipos_servicios")
public class Empleado_tiposServiciosEntities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_empleado", nullable = false)
    private Integer idEmpleado;

    @Column(name = "id_tipo_servicio", nullable = false)
    private Integer idTipoServicio;

}