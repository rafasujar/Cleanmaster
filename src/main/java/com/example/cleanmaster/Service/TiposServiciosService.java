package com.example.cleanmaster.Service;

import com.example.cleanmaster.models.dto.TiposServiciosDTO;

import java.util.List;

public interface TiposServiciosService {
    TiposServiciosDTO getTipoById(int id);

    List<TiposServiciosDTO> getAllTipos();

    List<TiposServiciosDTO> getEmpleadoServicos(Integer id);
}
