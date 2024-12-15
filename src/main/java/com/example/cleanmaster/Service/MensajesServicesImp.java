package com.example.cleanmaster.Service;

import com.example.cleanmaster.Repository.MensajesRepository;
import com.example.cleanmaster.models.dto.MensajeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MensajesServicesImp implements MensajesService {
    @Autowired
    private MensajesRepository mensajesRepository;

    @Override
    public List<MensajeDTO> findAllByIdEmpleado(Integer idEmpleado) {
       if (mensajesRepository.findAllByIdEmpleado(idEmpleado).isPresent()) {
           return mensajesRepository.findAllByIdEmpleado(idEmpleado).get().stream().map(MensajeDTO::ConvertToDTO).collect(Collectors.toList());
       }
       return  null;
    }

    @Override
    public List<MensajeDTO> findAllByIdCliente(Integer idcliente) {
        if (mensajesRepository.findAllByIdCliente(idcliente).isPresent()) {
            return mensajesRepository.findAllByIdCliente(idcliente).get().stream().map(MensajeDTO::ConvertToDTO).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public void save(MensajeDTO me) {
        mensajesRepository.save(MensajeDTO.ConvertToEntities(me));
    }
}
