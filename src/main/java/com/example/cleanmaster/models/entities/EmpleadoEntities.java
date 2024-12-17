package com.example.cleanmaster.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "empleado")
public class EmpleadoEntities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "Nombre", nullable = false, length = 50)
    private String Nombre;

    @Column(name = "Apellidos", nullable = false, length = 50)
    private String Apellidos;

    @Column(name = "numss", nullable = false)
    private Integer numss;

    @Column(name = "iban", nullable = false, length = 34)
    private String iban;

    @Column(name = "movil", nullable = false, length = 15)
    private String movil;


    @Column(name = "id_Encargada")
    private Integer idEncargada;

    @Column(name = "correo", nullable = false, length = 200)
    private String correo;

    @Column(name = "password", nullable = false, length = 15)
    private String password;

    public EmpleadoEntities() {
    }


    public Integer getId() {
        return this.id;
    }

    public String getNombre() {
        return this.Nombre;
    }

    public String getApellidos() {
        return this.Apellidos;
    }

    public Integer getNumss() {
        return this.numss;
    }

    public String getIban() {
        return this.iban;
    }

    public String getMovil() {
        return this.movil;
    }

    public Integer getIdEncargada() {
        return this.idEncargada;
    }

    public String getCorreo() {
        return this.correo;
    }

    public String getPassword() {
        return this.password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public void setNumss(Integer numss) {
        this.numss = numss;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public void setIdEncargada(Integer idEncargada) {
        this.idEncargada = idEncargada;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof EmpleadoEntities)) return false;
        final EmpleadoEntities other = (EmpleadoEntities) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$Nombre = this.getNombre();
        final Object other$Nombre = other.getNombre();
        if (this$Nombre == null ? other$Nombre != null : !this$Nombre.equals(other$Nombre)) return false;
        final Object this$Apellidos = this.getApellidos();
        final Object other$Apellidos = other.getApellidos();
        if (this$Apellidos == null ? other$Apellidos != null : !this$Apellidos.equals(other$Apellidos)) return false;
        final Object this$numss = this.getNumss();
        final Object other$numss = other.getNumss();
        if (this$numss == null ? other$numss != null : !this$numss.equals(other$numss)) return false;
        final Object this$iban = this.getIban();
        final Object other$iban = other.getIban();
        if (this$iban == null ? other$iban != null : !this$iban.equals(other$iban)) return false;
        final Object this$movil = this.getMovil();
        final Object other$movil = other.getMovil();
        if (this$movil == null ? other$movil != null : !this$movil.equals(other$movil)) return false;
        final Object this$idEncargada = this.getIdEncargada();
        final Object other$idEncargada = other.getIdEncargada();
        if (this$idEncargada == null ? other$idEncargada != null : !this$idEncargada.equals(other$idEncargada))
            return false;
        final Object this$correo = this.getCorreo();
        final Object other$correo = other.getCorreo();
        if (this$correo == null ? other$correo != null : !this$correo.equals(other$correo)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof EmpleadoEntities;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $Nombre = this.getNombre();
        result = result * PRIME + ($Nombre == null ? 43 : $Nombre.hashCode());
        final Object $Apellidos = this.getApellidos();
        result = result * PRIME + ($Apellidos == null ? 43 : $Apellidos.hashCode());
        final Object $numss = this.getNumss();
        result = result * PRIME + ($numss == null ? 43 : $numss.hashCode());
        final Object $iban = this.getIban();
        result = result * PRIME + ($iban == null ? 43 : $iban.hashCode());
        final Object $movil = this.getMovil();
        result = result * PRIME + ($movil == null ? 43 : $movil.hashCode());
        final Object $idEncargada = this.getIdEncargada();
        result = result * PRIME + ($idEncargada == null ? 43 : $idEncargada.hashCode());
        final Object $correo = this.getCorreo();
        result = result * PRIME + ($correo == null ? 43 : $correo.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        return result;
    }

    public String toString() {
        return "EmpleadoEntities(id=" + this.getId() + ", Nombre=" + this.getNombre() + ", Apellidos=" + this.getApellidos() + ", numss=" + this.getNumss() + ", iban=" + this.getIban() + ", movil=" + this.getMovil() + ", idEncargada=" + this.getIdEncargada() + ", correo=" + this.getCorreo() + ", password=" + this.getPassword() + ")";
    }
}