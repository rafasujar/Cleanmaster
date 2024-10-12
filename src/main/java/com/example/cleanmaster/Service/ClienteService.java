package com.example.cleanmaster.Service;

import com.example.cleanmaster.models.dto.ClienteDTO;

public interface ClienteService {

    ClienteDTO logearCliente(ClienteDTO clienteDTO);
    void registrarCliente(ClienteDTO clienteDTO);
    void actualizarCliente(ClienteDTO clienteDTO);
    boolean existeCliente(String correo);





}
