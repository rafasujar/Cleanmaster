package com.example.cleanmaster.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "mensaje")
public class MensajeEntities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "idCliente", nullable = false)
    private Integer idCliente;

    @Column(name = "idEmpleado", nullable = false)
    private Integer idEmpleado;

    @Column(name = "asunto", nullable = false, length = 100)
    private String asunto;

    @Column(name = "mensaje", nullable = false, length = 500)
    private String mensaje;

}