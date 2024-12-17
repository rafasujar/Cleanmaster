package com.example.cleanmaster.models.dto;

import com.example.cleanmaster.models.entities.MensajeEntities;

public class MensajeDTO {

    private Integer id;

    private Integer idCliente;

    private Integer idEmpleado;

    private String asunto;

    private String mensaje;

    private String emisor;

    private String receptor;

    public MensajeDTO() {
    }

    public static MensajeDTO ConvertToDTO(MensajeEntities mensaje) {
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

    public static MensajeEntities ConvertToEntities(MensajeDTO mensajeDTO) {
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

    public Integer getId() {
        return this.id;
    }

    public Integer getIdCliente() {
        return this.idCliente;
    }

    public Integer getIdEmpleado() {
        return this.idEmpleado;
    }

    public String getAsunto() {
        return this.asunto;
    }

    public String getMensaje() {
        return this.mensaje;
    }

    public String getEmisor() {
        return this.emisor;
    }

    public String getReceptor() {
        return this.receptor;
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

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof MensajeDTO)) return false;
        final MensajeDTO other = (MensajeDTO) o;
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
        final Object this$asunto = this.getAsunto();
        final Object other$asunto = other.getAsunto();
        if (this$asunto == null ? other$asunto != null : !this$asunto.equals(other$asunto)) return false;
        final Object this$mensaje = this.getMensaje();
        final Object other$mensaje = other.getMensaje();
        if (this$mensaje == null ? other$mensaje != null : !this$mensaje.equals(other$mensaje)) return false;
        final Object this$emisor = this.getEmisor();
        final Object other$emisor = other.getEmisor();
        if (this$emisor == null ? other$emisor != null : !this$emisor.equals(other$emisor)) return false;
        final Object this$receptor = this.getReceptor();
        final Object other$receptor = other.getReceptor();
        if (this$receptor == null ? other$receptor != null : !this$receptor.equals(other$receptor)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MensajeDTO;
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
        final Object $asunto = this.getAsunto();
        result = result * PRIME + ($asunto == null ? 43 : $asunto.hashCode());
        final Object $mensaje = this.getMensaje();
        result = result * PRIME + ($mensaje == null ? 43 : $mensaje.hashCode());
        final Object $emisor = this.getEmisor();
        result = result * PRIME + ($emisor == null ? 43 : $emisor.hashCode());
        final Object $receptor = this.getReceptor();
        result = result * PRIME + ($receptor == null ? 43 : $receptor.hashCode());
        return result;
    }

    public String toString() {
        return "MensajeDTO(id=" + this.getId() + ", idCliente=" + this.getIdCliente() + ", idEmpleado=" + this.getIdEmpleado() + ", asunto=" + this.getAsunto() + ", mensaje=" + this.getMensaje() + ", emisor=" + this.getEmisor() + ", receptor=" + this.getReceptor() + ")";
    }
}