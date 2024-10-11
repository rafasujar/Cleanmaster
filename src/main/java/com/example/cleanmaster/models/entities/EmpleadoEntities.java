package com.example.cleanmaster.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "empleado")
public class EmpleadoEntities {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "Nombre", nullable = false, length = 50)
    private String Nombre;

    @Column(name = "Apellidos", nullable = false, length = 50)
    private String Apellidos;

    @Column(name = "numss", nullable = false)
    private Integer numss;

    @Column(name = "iban", nullable = false, length = 34)
    private String iban;

    @Column(name = "movil", nullable = false, length = 15)
    private String movil;


    @Column(name = "id_Encargada" )
    private Integer idEncargada;

    @Column(name = "correo", nullable = false, length = 200)
    private String correo;

    @Column(name = "password", nullable = false, length = 15)
    private String password;


}