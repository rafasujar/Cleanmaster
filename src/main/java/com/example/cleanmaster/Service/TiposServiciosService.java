package com.example.cleanmaster.Service;

import com.example.cleanmaster.models.dto.TiposServiciosDTO;

import java.util.List;

public interface TiposServiciosService {
    TiposServiciosDTO getTipoById(Integer id);
    List<TiposServiciosDTO> getAllTipos();
}
