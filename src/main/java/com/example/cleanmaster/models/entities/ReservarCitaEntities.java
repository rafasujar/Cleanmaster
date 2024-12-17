package com.example.cleanmaster.models.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reservar_cita")
public class ReservarCitaEntities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_Cliente", nullable = false)
    private Integer idCliente;

    @Column(name = "id_Empleado", nullable = true)
    private Integer idEmpleado;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "id_Tipo_Servicio", nullable = false)
    private Integer idTipoServicio;

    @Column(name = "id_Direccion", nullable = false, length = 200)
    private Integer idDireccion;

    @Column(name = "especificaciones", length = 200)
    private String especificaciones;

    @Column(name = "finalizada_reserva", nullable = false)
    private Boolean finalizadaReserva = false;

    public ReservarCitaEntities() {
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getIdCliente() {
        return this.idCliente;
    }

    public Integer getIdEmpleado() {
        return this.idEmpleado;
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public Integer getIdTipoServicio() {
        return this.idTipoServicio;
    }

    public Integer getIdDireccion() {
        return this.idDireccion;
    }

    public String getEspecificaciones() {
        return this.especificaciones;
    }

    public Boolean getFinalizadaReserva() {
        return this.finalizadaReserva;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setIdTipoServicio(Integer idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public void setIdDireccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }

    public void setEspecificaciones(String especificaciones) {
        this.especificaciones = especificaciones;
    }

    public void setFinalizadaReserva(Boolean finalizadaReserva) {
        this.finalizadaReserva = finalizadaReserva;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ReservarCitaEntities)) return false;
        final ReservarCitaEntities other = (ReservarCitaEntities) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$idCliente = this.getIdCliente();
        final Object other$idCliente = other.getIdCliente();
        if (this$idCliente == null ? other$idCliente != null : !this$idCliente.equals(other$idCliente)) return false;
        final Object this$idEmpleado = this.getIdEmpleado();
        final Object other$idEmpleado = other.getIdEmpleado();
        if (this$idEmpleado == null ? other$idEmpleado != null : !this$idEmpleado.equals(other$idEmpleado))
            return false;
        final Object this$fecha = this.getFecha();
        final Object other$fecha = other.getFecha();
        if (this$fecha == null ? other$fecha != null : !this$fecha.equals(other$fecha)) return false;
        final Object this$idTipoServicio = this.getIdTipoServicio();
        final Object other$idTipoServicio = other.getIdTipoServicio();
        if (this$idTipoServicio == null ? other$idTipoServicio != null : !this$idTipoServicio.equals(other$idTipoServicio))
            return false;
        final Object this$idDireccion = this.getIdDireccion();
        final Object other$idDireccion = other.getIdDireccion();
        if (this$idDireccion == null ? other$idDireccion != null : !this$idDireccion.equals(other$idDireccion))
            return false;
        final Object this$especificaciones = this.getEspecificaciones();
        final Object other$especificaciones = other.getEspecificaciones();
        if (this$especificaciones == null ? other$especificaciones != null : !this$especificaciones.equals(other$especificaciones))
            return false;
        final Object this$finalizadaReserva = this.getFinalizadaReserva();
        final Object other$finalizadaReserva = other.getFinalizadaReserva();
        if (this$finalizadaReserva == null ? other$finalizadaReserva != null : !this$finalizadaReserva.equals(other$finalizadaReserva))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ReservarCitaEntities;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $idCliente = this.getIdCliente();
        result = result * PRIME + ($idCliente == null ? 43 : $idCliente.hashCode());
        final Object $idEmpleado = this.getIdEmpleado();
        result = result * PRIME + ($idEmpleado == null ? 43 : $idEmpleado.hashCode());
        final Object $fecha = this.getFecha();
        result = result * PRIME + ($fecha == null ? 43 : $fecha.hashCode());
        final Object $idTipoServicio = this.getIdTipoServicio();
        result = result * PRIME + ($idTipoServicio == null ? 43 : $idTipoServicio.hashCode());
        final Object $idDireccion = this.getIdDireccion();
        result = result * PRIME + ($idDireccion == null ? 43 : $idDireccion.hashCode());
        final Object $especificaciones = this.getEspecificaciones();
        result = result * PRIME + ($especificaciones == null ? 43 : $especificaciones.hashCode());
        final Object $finalizadaReserva = this.getFinalizadaReserva();
        result = result * PRIME + ($finalizadaReserva == null ? 43 : $finalizadaReserva.hashCode());
        return result;
    }

    public String toString() {
        return "ReservarCitaEntities(id=" + this.getId() + ", idCliente=" + this.getIdCliente() + ", idEmpleado=" + this.getIdEmpleado() + ", fecha=" + this.getFecha() + ", idTipoServicio=" + this.getIdTipoServicio() + ", idDireccion=" + this.getIdDireccion() + ", especificaciones=" + this.getEspecificaciones() + ", finalizadaReserva=" + this.getFinalizadaReserva() + ")";
    }
}