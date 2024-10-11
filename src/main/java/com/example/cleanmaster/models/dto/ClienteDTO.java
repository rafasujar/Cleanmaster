package com.example.cleanmaster.models.dto;

import com.example.cleanmaster.models.entities.ClienteEntities;
import lombok.Data;


@Data
public class ClienteDTO {
   
    private Integer id;

    private String nombre;

    private String correo;
   
    private String password;

    private String movil;

    private Integer idDirecciones;


    public static ClienteDTO ConvertToDTO( ClienteEntities entities ){
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId( entities.getId() );
        clienteDTO.setNombre(entities.getNombre());
        clienteDTO.setCorreo(entities.getCorreo());
        clienteDTO.setPassword(entities.getPassword());
        clienteDTO.setMovil(entities.getMovil());
        clienteDTO.setIdDirecciones(entities.getIdDirecciones());
        return clienteDTO;
    }

    public static ClienteEntities ConvertToEntities(ClienteDTO clienteDTO ){
        ClienteEntities cliente = new ClienteEntities();
        cliente.setId(clienteDTO.getId());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setCorreo(clienteDTO.getCorreo());
        cliente.setPassword(clienteDTO.getPassword());
        cliente.setMovil(clienteDTO.getMovil());
        cliente.setIdDirecciones(clienteDTO.getIdDirecciones());
        return cliente;
    }


}