package com.example.cleanmaster.models.dto;

import com.example.cleanmaster.models.entities.MensajeEntities;

import lombok.Data;

@Data
public class MensajeDTO {

    private Integer id;

    private Integer idCliente;

    private Integer idEmpleado;

    private String asunto;

    private String mensaje;

    private String emisor;

    private String receptor;

    public static MensajeDTO ConvertToDTO(MensajeEntities mensaje){
        MensajeDTO mensajeDTO = new MensajeDTO();
        mensajeDTO.setId(mensaje.getId());
        mensajeDTO.setIdCliente(mensaje.getIdCliente());
        mensajeDTO.setIdEmpleado(mensaje.getIdEmpleado());
        mensajeDTO.setAsunto(mensaje.getAsunto());
        mensajeDTO.setMensaje(mensaje.getMensaje());
        mensajeDTO.setEmisor(mensaje.getEmisor());
        mensajeDTO.setReceptor(mensaje.getReceptor());
        return mensajeDTO;
    }

    public static MensajeEntities ConvertToEntities(MensajeDTO mensajeDTO){
        MensajeEntities mensaje = new MensajeEntities();
        mensaje.setId(mensajeDTO.getId());
        mensaje.setIdCliente(mensajeDTO.getIdCliente());
        mensaje.setIdEmpleado(mensajeDTO.getIdEmpleado());
        mensaje.setAsunto(mensajeDTO.getAsunto());
        mensaje.setMensaje(mensajeDTO.getMensaje());
        mensaje.setEmisor(mensajeDTO.getEmisor());
        mensaje.setReceptor(mensajeDTO.getReceptor());
        return mensaje;
    }

}