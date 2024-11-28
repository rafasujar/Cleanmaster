package com.example.cleanmaster.Service;

import com.example.cleanmaster.Repository.TiposServiciosRepository;
import com.example.cleanmaster.models.dto.TiposServiciosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TiposServiciosServiceImp implements TiposServiciosService {
     @Autowired
    private TiposServiciosRepository tiposServiciosRepository;

    @Override
    public TiposServiciosDTO getTipoById(Integer id) {
        Optional<TiposServiciosDTO> servicios = Optional.of( tiposServiciosRepository.getTiposServiciosEntitiesById(id));
        return servicios.orElse(null);
    }

    @Override
    public List<TiposServiciosDTO> getAllTipos() {
        return tiposServiciosRepository.findAll().stream().map(TiposServiciosDTO::ConvertToDTO).toList();
    }
}
