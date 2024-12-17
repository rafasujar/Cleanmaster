package com.example.cleanmaster.Service;

import com.example.cleanmaster.models.dto.MensajeDTO;

import java.util.List;

public interface MensajesService {

    List<MensajeDTO> findAllByIdEmpleado(Integer idEmpleado);

    List<MensajeDTO> findAllByIdCliente(Integer idcliente);

    void save(MensajeDTO me);
}
