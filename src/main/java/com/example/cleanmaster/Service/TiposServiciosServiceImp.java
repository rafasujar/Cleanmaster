package com.example.cleanmaster.Service;

import com.example.cleanmaster.Repository.TiposServiciosRepository;
import com.example.cleanmaster.models.dto.TiposServiciosDTO;
import com.example.cleanmaster.models.entities.TiposServiciosEntities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TiposServiciosServiceImp implements TiposServiciosService {
     @Autowired
    private TiposServiciosRepository tiposServiciosRepository;

    @Override
    public TiposServiciosDTO getTipoById(int id) {
        Optional<TiposServiciosEntities> servicios =  tiposServiciosRepository.findById(id);
        if (servicios.isPresent())
            return TiposServiciosDTO.ConvertToDTO(servicios.get());
        else
            return null;
    }

    @Override
    public List<TiposServiciosDTO> getAllTipos() {
        return tiposServiciosRepository.findAll().stream().map(TiposServiciosDTO::ConvertToDTO).toList();
    }
}
