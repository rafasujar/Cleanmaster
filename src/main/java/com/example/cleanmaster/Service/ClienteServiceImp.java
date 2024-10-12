package com.example.cleanmaster.Service;

import com.example.cleanmaster.Repository.ClienteRepository;
import com.example.cleanmaster.models.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImp implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public ClienteDTO logearCliente(ClienteDTO clienteDTO) {
        if (clienteRepository.findByCorreoAndPassword(clienteDTO.getCorreo(), clienteDTO.getPassword()).isPresent()) {
            return ClienteDTO.ConvertToDTO(clienteRepository.findByCorreoAndPassword(clienteDTO.getCorreo(), clienteDTO.getPassword()).get());
        }
        return null;
    }

    @Override
    public void registrarCliente(ClienteDTO clienteDTO) {
         clienteRepository.save(ClienteDTO.ConvertToEntities(clienteDTO));
    }

    @Override
    public void actualizarCliente(ClienteDTO clienteDTO) {
       //no implementado
    }

    @Override
    public boolean existeCliente(String correo) {
        return clienteRepository.findByCorreo(correo).isPresent();
    }
}
