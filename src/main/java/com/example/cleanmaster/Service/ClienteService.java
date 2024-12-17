package com.example.cleanmaster.Service;

import com.example.cleanmaster.models.dto.ClienteDTO;

public interface ClienteService {

    ClienteDTO logearCliente(ClienteDTO clienteDTO);

    void registrarCliente(ClienteDTO clienteDTO);

    ClienteDTO existeCliente(String correo);

    ClienteDTO findById(Integer id);

    void save(ClienteDTO clienteDTO);
}
