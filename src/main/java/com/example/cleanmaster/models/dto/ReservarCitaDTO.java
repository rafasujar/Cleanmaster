package com.example.cleanmaster.models.dto;

import com.example.cleanmaster.models.entities.ReservarCitaEntities;

import java.time.LocalDate;

public class ReservarCitaDTO {

    private Integer id;

    private Integer idCliente;

    private Integer idEmpleado;

    private LocalDate fecha;

    private Integer idTipoServicio;

    private Integer idDireccion;

    private String especificaciones;

    private Boolean finalizadaReserva = false;

    public ReservarCitaDTO() {
    }


    public static ReservarCitaDTO ConvertToDTO(ReservarCitaEntities entities) {
        ReservarCitaDTO dto = new ReservarCitaDTO();
        dto.setId(entities.getId());
        dto.setIdCliente(entities.getIdCliente());
        dto.setIdEmpleado(entities.getIdEmpleado());
        dto.setFecha(entities.getFecha());
        dto.setIdTipoServicio(entities.getIdTipoServicio());
        dto.setIdDireccion(entities.getIdDireccion());
        dto.setFinalizadaReserva(entities.getFinalizadaReserva());
        dto.setEspecificaciones(entities.getEspecificaciones());
        return dto;
    }

    public static ReservarCitaEntities ConvertToEntities(ReservarCitaDTO dto) {
        ReservarCitaEntities entities = new ReservarCitaEntities();
        entities.setId(dto.getId());
        entities.setIdCliente(dto.getIdCliente());
        entities.setIdEmpleado(dto.getIdEmpleado());
        entities.setFecha(dto.getFecha());
        entities.setIdTipoServicio(dto.getIdTipoServicio());
        entities.setIdDireccion(dto.getIdDireccion());
        entities.setFinalizadaReserva(dto.getFinalizadaReserva());
        entities.setEspecificaciones(dto.getEspecificaciones());
        return entities;
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
        if (!(o instanceof ReservarCitaDTO)) return false;
        final ReservarCitaDTO other = (ReservarCitaDTO) o;
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
        return other instanceof ReservarCitaDTO;
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
        return "ReservarCitaDTO(id=" + this.getId() + ", idCliente=" + this.getIdCliente() + ", idEmpleado=" + this.getIdEmpleado() + ", fecha=" + this.getFecha() + ", idTipoServicio=" + this.getIdTipoServicio() + ", idDireccion=" + this.getIdDireccion() + ", especificaciones=" + this.getEspecificaciones() + ", finalizadaReserva=" + this.getFinalizadaReserva() + ")";
    }
}