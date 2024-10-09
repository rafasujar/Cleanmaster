package com.example.cleanmaster.models.dto;

import com.example.cleanmaster.models.entities.EmpleadoEntities;
import lombok.Data;

@Data
public class EmpleadoDTO {

    private Integer id;

    private String Nombre;

    private String Apellidos;

    private Integer numss;

    private String iban;

    private String movil;

    private String correo;

    private Integer idEncargada;

    private String password;

    public static EmpleadoDTO ConvertToDTO(EmpleadoEntities empleadoEntities) {
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        empleadoDTO.setId(empleadoEntities.getId());
        empleadoDTO.setNombre(empleadoEntities.getNombre());
        empleadoDTO.setApellidos(empleadoEntities.getApellidos());
        empleadoDTO.setNumss(empleadoEntities.getNumss());
        empleadoDTO.setIban(empleadoEntities.getIban());
        empleadoDTO.setMovil(empleadoEntities.getMovil());
        empleadoDTO.setCorreo(empleadoEntities.getCorreo());
        empleadoDTO.setIdEncargada(empleadoEntities.getIdEncargada());
        empleadoDTO.setPassword(empleadoEntities.getPassword());
        return empleadoDTO;
    }

    public static EmpleadoEntities ConvertToEntities(EmpleadoDTO empleadoDTO) {
        EmpleadoEntities empleadoEntities = new EmpleadoEntities();
        empleadoEntities.setId(empleadoDTO.getId());
        empleadoEntities.setNombre(empleadoDTO.getNombre());
        empleadoEntities.setApellidos(empleadoDTO.getApellidos());
        empleadoEntities.setCorreo(empleadoDTO.getCorreo());
        empleadoEntities.setNumss(empleadoDTO.getNumss());
        empleadoEntities.setIban(empleadoDTO.getIban());
        empleadoEntities.setMovil(empleadoDTO.getMovil());
        empleadoEntities.setIdEncargada(empleadoDTO.getIdEncargada());
        empleadoEntities.setPassword(empleadoDTO.getPassword());
        return empleadoEntities;
    }

}