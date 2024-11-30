package com.example.cleanmaster.models.dto;

import com.example.cleanmaster.models.entities.ReservarCitaEntities;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservarCitaDTO {

    private Integer id;

    private Integer idCliente;

    private Integer idEmpleado;

    private LocalDate fecha;

    private Integer idTipoServicio;

    private Integer idDireccion;

    private String especificaciones;


    public static ReservarCitaDTO ConvertToDTO(ReservarCitaEntities entities){
        ReservarCitaDTO dto = new ReservarCitaDTO();
        dto.setId(entities.getId());
        dto.setIdCliente(entities.getIdCliente());
        dto.setIdEmpleado(entities.getIdEmpleado());
        dto.setFecha(entities.getFecha());
        dto.setIdTipoServicio(entities.getIdTipoServicio());
        dto.setDireccion(entities.getDireccion());
        dto.setEspecificaciones(entities.getEspecificaciones());
        return dto;
    }

    public static ReservarCitaEntities ConvertToEntities(ReservarCitaDTO dto){
        ReservarCitaEntities entities = new ReservarCitaEntities();
        entities.setId(dto.getId());
        entities.setIdCliente(dto.getIdCliente());
        entities.setIdEmpleado(dto.getIdEmpleado());
        entities.setFecha(dto.getFecha());
        entities.setIdTipoServicio(dto.getIdTipoServicio());
        entities.setDireccion(dto.getDireccion());
        entities.setEspecificaciones(dto.getEspecificaciones());
        return entities;
    }
}