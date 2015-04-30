package com.example.vivanco.tasmc;

/**
 * Created by VIVANCO on 17/03/2015.
 */
public class Usuario {
    private int idusuario;
    private String email;
    private String categoria;
    private String clase;
    private String tipo;
    private String tipoVuelo;
    private int Itinerario_idItinerario;
    private int Equipaje_idEquipaje;

    public Usuario(int idusuario, String email, String categoria, String clase, String tipo, String tipoVuelo, int itinerario_idItinerario, int equipaje_idEquipaje) {
        this.idusuario = idusuario;
        this.email = email;
        this.categoria = categoria;
        this.clase = clase;
        this.tipo = tipo;
        this.tipoVuelo = tipoVuelo;
        Itinerario_idItinerario = itinerario_idItinerario;
        Equipaje_idEquipaje = equipaje_idEquipaje;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idusuario=" + idusuario +
                ", email='" + email + '\'' +
                ", categoria='" + categoria + '\'' +
                ", clase='" + clase + '\'' +
                ", tipo='" + tipo + '\'' +
                ", tipoVuelo='" + tipoVuelo + '\'' +
                ", Itinerario_idItinerario=" + Itinerario_idItinerario +
                ", Equipaje_idEquipaje=" + Equipaje_idEquipaje +
                '}';
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTipoVuelo(String tipoVuelo) {
        this.tipoVuelo = tipoVuelo;
    }

    public void setItinerario_idItinerario(int itinerario_idItinerario) {
        Itinerario_idItinerario = itinerario_idItinerario;
    }

    public void setEquipaje_idEquipaje(int equipaje_idEquipaje) {
        Equipaje_idEquipaje = equipaje_idEquipaje;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public String getEmail() {
        return email;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getClase() {
        return clase;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTipoVuelo() {
        return tipoVuelo;
    }

    public int getItinerario_idItinerario() {
        return Itinerario_idItinerario;
    }

    public int getEquipaje_idEquipaje() {
        return Equipaje_idEquipaje;
    }
}
