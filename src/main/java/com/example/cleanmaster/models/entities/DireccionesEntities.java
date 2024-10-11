package com.example.cleanmaster.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "direcciones")
public class DireccionesEntities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "idCliente", nullable = false)
    private Integer idCliente;

    @Column(name = "direccion", nullable = false, length = 200)
    private String direccion;

}