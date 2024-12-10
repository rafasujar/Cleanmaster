package com.example.cleanmaster.Service;

import com.example.cleanmaster.models.dto.DireccionesDTO;

import java.util.List;

public interface DireccionesService {
    DireccionesDTO getDireccionById(Integer id);

    List<DireccionesDTO> findAllByIdCliente(Integer id);

    void save(DireccionesDTO direccionesDTO);
    void delete (DireccionesDTO direccionesDTO);
}
