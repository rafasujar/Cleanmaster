package com.example.cleanmaster.models.dto;

import com.example.cleanmaster.models.entities.DireccionesEntities;
import lombok.Data;

@Data
public class DireccionesDTO {

    private Integer id;

    private Integer idCliente;

    private String direccion;

    public static DireccionesDTO ConvertToDTO(DireccionesEntities direcciones){
        DireccionesDTO direccionesDTO = new DireccionesDTO();
        direccionesDTO.setId(direcciones.getId());
        direccionesDTO.setIdCliente(direcciones.getIdCliente());
        direccionesDTO.setDireccion(direcciones.getDireccion());
        return direccionesDTO;
    }

    public static DireccionesEntities ConvertToEntities(DireccionesDTO direccionesDTO){
        DireccionesEntities direcciones = new DireccionesEntities();
        direcciones.setId(direccionesDTO.getId());
        direcciones.setIdCliente(direccionesDTO.getIdCliente());
        direcciones.setDireccion(direccionesDTO.getDireccion());
        return direcciones;
    }

}