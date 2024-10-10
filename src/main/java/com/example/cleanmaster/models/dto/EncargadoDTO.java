package com.example.cleanmaster.models.dto;

import lombok.Data;

@Data
public class EncargadoDTO {

    private Integer id;

    private Integer idEmpleados;

    public static EncargadoDTO ConvertToDTO(EncargadoEntities encargadoEntities) {
        EncargadoDTO encargadoDTO = new EncargadoDTO();
        encargadoDTO.setId(encargadoEntities.getId());
        encargadoDTO.setIdEmpleados(encargadoEntities.getIdEmpleados());
        return encargadoDTO;
    }

    public static EncargadoEntities ConvertToEntities(EncargadoDTO encargadoDTO) {
        EncargadoEntities encargadoEntities = new EncargadoEntities();
        encargadoEntities.setId(encargadoDTO.getId());
        encargadoEntities.setIdEmpleados(encargadoDTO.getIdEmpleados());
        return encargadoEntities;
    }

}