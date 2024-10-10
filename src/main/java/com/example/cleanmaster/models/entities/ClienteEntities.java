package com.example.cleanmaster.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class ClienteEntities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 20)
    private String nombre;

    @Column(name = "correo", nullable = false, length = 200)
    private String correo;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "movil", nullable = false, length = 15)
    private String movil;

    @Column(name = "idDirecciones")
    private Integer idDirecciones;

}