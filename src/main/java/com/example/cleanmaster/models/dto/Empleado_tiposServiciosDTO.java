package com.example.cleanmaster.models.dto;

import com.example.cleanmaster.models.entities.Empleado_tiposServiciosEntities;

public class Empleado_tiposServiciosDTO {

    private Integer id;

    private Integer idEmpleado;

    private Integer idTipoServicio;

    public Empleado_tiposServiciosDTO() {
    }

    public static Empleado_tiposServiciosDTO ConvertToDTO(Empleado_tiposServiciosEntities entities) {
        Empleado_tiposServiciosDTO dto = new Empleado_tiposServiciosDTO();
        dto.setId(entities.getId());
        dto.setIdEmpleado(entities.getIdEmpleado());
        dto.setIdTipoServicio(entities.getIdTipoServicio());
        return dto;
    }

    public static Empleado_tiposServiciosEntities ConvertToEntities(Empleado_tiposServiciosDTO dto) {
        Empleado_tiposServiciosEntities entities = new Empleado_tiposServiciosEntities();
        entities.setId(dto.getId());
        entities.setIdEmpleado(dto.getIdEmpleado());
        entities.setIdTipoServicio(dto.getIdTipoServicio());
        return entities;
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getIdEmpleado() {
        return this.idEmpleado;
    }

    public Integer getIdTipoServicio() {
        return this.idTipoServicio;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public void setIdTipoServicio(Integer idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Empleado_tiposServiciosDTO)) return false;
        final Empleado_tiposServiciosDTO other = (Empleado_tiposServiciosDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$idEmpleado = this.getIdEmpleado();
        final Object other$idEmpleado = other.getIdEmpleado();
        if (this$idEmpleado == null ? other$idEmpleado != null : !this$idEmpleado.equals(other$idEmpleado))
            return false;
        final Object this$idTipoServicio = this.getIdTipoServicio();
        final Object other$idTipoServicio = other.getIdTipoServicio();
        if (this$idTipoServicio == null ? other$idTipoServicio != null : !this$idTipoServicio.equals(other$idTipoServicio))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Empleado_tiposServiciosDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $idEmpleado = this.getIdEmpleado();
        result = result * PRIME + ($idEmpleado == null ? 43 : $idEmpleado.hashCode());
        final Object $idTipoServicio = this.getIdTipoServicio();
        result = result * PRIME + ($idTipoServicio == null ? 43 : $idTipoServicio.hashCode());
        return result;
    }

    public String toString() {
        return "Empleado_tiposServiciosDTO(id=" + this.getId() + ", idEmpleado=" + this.getIdEmpleado() + ", idTipoServicio=" + this.getIdTipoServicio() + ")";
    }
}