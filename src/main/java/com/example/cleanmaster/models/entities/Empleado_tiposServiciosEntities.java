package com.example.cleanmaster.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "empleado_tipos_servicios")
public class Empleado_tiposServiciosEntities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_empleado", nullable = false)
    private Integer idEmpleado;

    @Column(name = "id_tipo_servicio", nullable = false)
    private Integer idTipoServicio;

    public Empleado_tiposServiciosEntities() {
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
        if (!(o instanceof Empleado_tiposServiciosEntities)) return false;
        final Empleado_tiposServiciosEntities other = (Empleado_tiposServiciosEntities) o;
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
        return other instanceof Empleado_tiposServiciosEntities;
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
        return "Empleado_tiposServiciosEntities(id=" + this.getId() + ", idEmpleado=" + this.getIdEmpleado() + ", idTipoServicio=" + this.getIdTipoServicio() + ")";
    }
}