package com.example.cleanmaster.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ENCARGADO")
public class EncargadoEntities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @Column(name = "id_empleado")
    private Integer idEmpleados;

}