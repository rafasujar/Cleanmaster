package com.example.cleanmaster.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ENCARGADO")
public class EncargadoEntities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @Column(name = "id_empleado")
    private Integer idEmpleados;

    public EncargadoEntities() {
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getIdEmpleados() {
        return this.idEmpleados;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdEmpleados(Integer idEmpleados) {
        this.idEmpleados = idEmpleados;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof EncargadoEntities)) return false;
        final EncargadoEntities other = (EncargadoEntities) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$idEmpleados = this.getIdEmpleados();
        final Object other$idEmpleados = other.getIdEmpleados();
        if (this$idEmpleados == null ? other$idEmpleados != null : !this$idEmpleados.equals(other$idEmpleados))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof EncargadoEntities;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $idEmpleados = this.getIdEmpleados();
        result = result * PRIME + ($idEmpleados == null ? 43 : $idEmpleados.hashCode());
        return result;
    }

    public String toString() {
        return "EncargadoEntities(id=" + this.getId() + ", idEmpleados=" + this.getIdEmpleados() + ")";
    }
}