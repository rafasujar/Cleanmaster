package com.example.cleanmaster.Service;

import com.example.cleanmaster.Repository.ClienteRepository;
import com.example.cleanmaster.models.dto.ClienteDTO;
import com.example.cleanmaster.models.entities.ClienteEntities;
import com.example.cleanmaster.utils.utilsCleanMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImp implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public ClienteDTO logearCliente(ClienteDTO clienteDTO) {
        Optional<ClienteEntities> clienteEntities = clienteRepository.findByCorreo(clienteDTO.getCorreo());
        if (clienteEntities.isPresent()) {
            ClienteEntities cliente = clienteEntities.get();
            String password = utilsCleanMaster.decodeBase54(cliente.getPassword());
            if (password.equals(clienteDTO.getPassword())) {
                return ClienteDTO.ConvertToDTO(cliente);
            }
        }
        return null;
    }

    @Override
    public void registrarCliente(ClienteDTO clienteDTO) {
         clienteRepository.save(ClienteDTO.ConvertToEntities(clienteDTO));
    }



    @Override
    public ClienteDTO existeCliente(String correo) {
        if (clienteRepository.findByCorreo(correo).isPresent()) {
            return ClienteDTO.ConvertToDTO(clienteRepository.findByCorreo(correo).get());
        }
        return null;
    }

    @Override
    public ClienteDTO findById(Integer id) {
        return clienteRepository.findById(id).map(ClienteDTO::ConvertToDTO).orElse(null);
    }

    @Override
    public void save(ClienteDTO clienteDTO) {
        clienteRepository.save(ClienteDTO.ConvertToEntities(clienteDTO));
    }
}
