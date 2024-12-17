package com.example.cleanmaster.models.dto;

import com.example.cleanmaster.models.entities.EncargadoEntities;

public class EncargadoDTO {

    private Integer id;

    private Integer idEmpleado;

    public EncargadoDTO() {
    }


    public static EncargadoDTO ConvertToDTO(EncargadoEntities encargadoEntities) {
        EncargadoDTO encargadoDTO = new EncargadoDTO();
        encargadoDTO.setId(encargadoEntities.getId());
        encargadoDTO.setIdEmpleado(encargadoEntities.getIdEmpleados());
        return encargadoDTO;
    }

    public static EncargadoEntities ConvertToEntities(EncargadoDTO encargadoDTO) {
        EncargadoEntities encargadoEntities = new EncargadoEntities();
        encargadoEntities.setId(encargadoDTO.getId());
        encargadoEntities.setIdEmpleados(encargadoDTO.getIdEmpleado());
        return encargadoEntities;
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getIdEmpleado() {
        return this.idEmpleado;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof EncargadoDTO)) return false;
        final EncargadoDTO other = (EncargadoDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$idEmpleado = this.getIdEmpleado();
        final Object other$idEmpleado = other.getIdEmpleado();
        if (this$idEmpleado == null ? other$idEmpleado != null : !this$idEmpleado.equals(other$idEmpleado))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof EncargadoDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $idEmpleado = this.getIdEmpleado();
        result = result * PRIME + ($idEmpleado == null ? 43 : $idEmpleado.hashCode());
        return result;
    }

    public String toString() {
        return "EncargadoDTO(id=" + this.getId() + ", idEmpleado=" + this.getIdEmpleado() + ")";
    }
}