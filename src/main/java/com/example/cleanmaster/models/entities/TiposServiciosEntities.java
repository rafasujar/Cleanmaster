package com.example.cleanmaster.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tipos_servicios")
public class TiposServiciosEntities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 20)
    private String nombre;

    public TiposServiciosEntities() {
    }

    public Integer getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof TiposServiciosEntities)) return false;
        final TiposServiciosEntities other = (TiposServiciosEntities) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$nombre = this.getNombre();
        final Object other$nombre = other.getNombre();
        if (this$nombre == null ? other$nombre != null : !this$nombre.equals(other$nombre)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TiposServiciosEntities;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $nombre = this.getNombre();
        result = result * PRIME + ($nombre == null ? 43 : $nombre.hashCode());
        return result;
    }

    public String toString() {
        return "TiposServiciosEntities(id=" + this.getId() + ", nombre=" + this.getNombre() + ")";
    }
}