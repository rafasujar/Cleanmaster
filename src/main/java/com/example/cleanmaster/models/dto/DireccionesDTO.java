package com.example.cleanmaster.models.dto;

import com.example.cleanmaster.models.entities.DireccionesEntities;

public class DireccionesDTO {

    private Integer id;

    private Integer idCliente;

    private String direccion;

    public DireccionesDTO() {
    }

    public static DireccionesDTO ConvertToDTO(DireccionesEntities direcciones) {
        DireccionesDTO direccionesDTO = new DireccionesDTO();
        direccionesDTO.setId(direcciones.getId());
        direccionesDTO.setIdCliente(direcciones.getIdCliente());
        direccionesDTO.setDireccion(direcciones.getDireccion());
        return direccionesDTO;
    }

    public static DireccionesEntities ConvertToEntities(DireccionesDTO direccionesDTO) {
        DireccionesEntities direcciones = new DireccionesEntities();
        direcciones.setId(direccionesDTO.getId());
        direcciones.setIdCliente(direccionesDTO.getIdCliente());
        direcciones.setDireccion(direccionesDTO.getDireccion());
        return direcciones;
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getIdCliente() {
        return this.idCliente;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof DireccionesDTO)) return false;
        final DireccionesDTO other = (DireccionesDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$idCliente = this.getIdCliente();
        final Object other$idCliente = other.getIdCliente();
        if (this$idCliente == null ? other$idCliente != null : !this$idCliente.equals(other$idCliente)) return false;
        final Object this$direccion = this.getDireccion();
        final Object other$direccion = other.getDireccion();
        if (this$direccion == null ? other$direccion != null : !this$direccion.equals(other$direccion)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DireccionesDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $idCliente = this.getIdCliente();
        result = result * PRIME + ($idCliente == null ? 43 : $idCliente.hashCode());
        final Object $direccion = this.getDireccion();
        result = result * PRIME + ($direccion == null ? 43 : $direccion.hashCode());
        return result;
    }

    public String toString() {
        return "DireccionesDTO(id=" + this.getId() + ", idCliente=" + this.getIdCliente() + ", direccion=" + this.getDireccion() + ")";
    }
}