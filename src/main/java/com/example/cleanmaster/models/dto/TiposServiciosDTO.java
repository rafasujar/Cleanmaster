package com.example.cleanmaster.models.dto;

import com.example.cleanmaster.models.entities.TiposServiciosEntities;
import lombok.Data;

@Data
public class TiposServiciosDTO {
    private Integer id;

    private String nombre;


    public static TiposServiciosDTO ConvertToDTO(TiposServiciosEntities entities){
        TiposServiciosDTO dto = new TiposServiciosDTO();
        dto.setId(entities.getId());
        dto.setNombre(entities.getNombre());
        return dto;
    }

    public static TiposServiciosEntities ConvertToEntities(TiposServiciosDTO dto){
        TiposServiciosEntities entities = new TiposServiciosEntities();
        entities.setId(dto.getId());
        entities.setNombre(dto.getNombre());
        return entities;
    }
}