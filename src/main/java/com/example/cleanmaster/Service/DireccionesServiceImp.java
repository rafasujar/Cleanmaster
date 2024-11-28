package com.example.cleanmaster.Service;

import com.example.cleanmaster.Repository.DireccionesRepository;
import com.example.cleanmaster.models.dto.DireccionesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DireccionesServiceImp implements DireccionesService {

    @Autowired
    private DireccionesRepository direccionesRepository;

    @Override
    public DireccionesDTO getDireccionById(Integer id) {
        return DireccionesDTO.ConvertToDTO(direccionesRepository.getDireccionesEntitiesById(id));
    }
}
