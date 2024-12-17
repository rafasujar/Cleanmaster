package com.example.cleanmaster.models.dto;

import com.example.cleanmaster.models.entities.ClienteEntities;


public class ClienteDTO {

    private Integer id;

    private String nombre;

    private String correo;

    private String password;

    private String movil;

    public ClienteDTO() {
    }


    public static ClienteDTO ConvertToDTO(ClienteEntities entities) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(entities.getId());
        clienteDTO.setNombre(entities.getNombre());
        clienteDTO.setCorreo(entities.getCorreo());
        clienteDTO.setPassword(entities.getPassword());
        clienteDTO.setMovil(entities.getMovil());

        return clienteDTO;
    }

    public static ClienteEntities ConvertToEntities(ClienteDTO clienteDTO) {
        ClienteEntities cliente = new ClienteEntities();
        cliente.setId(clienteDTO.getId());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setCorreo(clienteDTO.getCorreo());
        cliente.setPassword(clienteDTO.getPassword());
        cliente.setMovil(clienteDTO.getMovil());

        return cliente;
    }


    public Integer getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getCorreo() {
        return this.correo;
    }

    public String getPassword() {
        return this.password;
    }

    public String getMovil() {
        return this.movil;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ClienteDTO)) return false;
        final ClienteDTO other = (ClienteDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$nombre = this.getNombre();
        final Object other$nombre = other.getNombre();
        if (this$nombre == null ? other$nombre != null : !this$nombre.equals(other$nombre)) return false;
        final Object this$correo = this.getCorreo();
        final Object other$correo = other.getCorreo();
        if (this$correo == null ? other$correo != null : !this$correo.equals(other$correo)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        final Object this$movil = this.getMovil();
        final Object other$movil = other.getMovil();
        if (this$movil == null ? other$movil != null : !this$movil.equals(other$movil)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ClienteDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $nombre = this.getNombre();
        result = result * PRIME + ($nombre == null ? 43 : $nombre.hashCode());
        final Object $correo = this.getCorreo();
        result = result * PRIME + ($correo == null ? 43 : $correo.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $movil = this.getMovil();
        result = result * PRIME + ($movil == null ? 43 : $movil.hashCode());
        return result;
    }

    public String toString() {
        return "ClienteDTO(id=" + this.getId() + ", nombre=" + this.getNombre() + ", correo=" + this.getCorreo() + ", password=" + this.getPassword() + ", movil=" + this.getMovil() + ")";
    }
}