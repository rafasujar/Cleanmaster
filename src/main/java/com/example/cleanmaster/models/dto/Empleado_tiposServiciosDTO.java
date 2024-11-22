package com.example.cleanmaster.models.dto;

import com.example.cleanmaster.models.entities.Empleado_tiposServiciosEntities;
import lombok.Data;

@Data
public class Empleado_tiposServiciosDTO {

    private Integer id;

    private Integer idEmpleado;

    private Integer idTipoServicio;

    public static Empleado_tiposServiciosDTO ConvertToDTO(Empleado_tiposServiciosEntities entities){
        Empleado_tiposServiciosDTO dto = new Empleado_tiposServiciosDTO();
        dto.setId(entities.getId());
        dto.setIdEmpleado(entities.getIdEmpleado());
        dto.setIdTipoServicio(entities.getIdTipoServicio());
        return dto;
    }

    public static Empleado_tiposServiciosEntities ConvertToEntities(Empleado_tiposServiciosDTO dto){
        Empleado_tiposServiciosEntities entities = new Empleado_tiposServiciosEntities();
        entities.setId(dto.getId());
        entities.setIdEmpleado(dto.getIdEmpleado());
        entities.setIdTipoServicio(dto.getIdTipoServicio());
        return entities;
    }

}