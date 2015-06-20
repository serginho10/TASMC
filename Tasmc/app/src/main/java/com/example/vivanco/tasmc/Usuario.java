package com.example.vivanco.tasmc;

/**
 * Created by VIVANCO on 17/03/2015.
 */
public class Usuario {
    private String email;
    private String categoria;
    private String clase;
    private String tipo;
    private int Itinerario_idItinerario;
    private int Equipaje_idEquipaje;

    public Usuario(String email, String categoria, String clase, String tipo, int itinerario_idItinerario, int equipaje_idEquipaje) {
        this.email = email;
        this.categoria = categoria;
        this.clase = clase;
        this.tipo = tipo;
        Itinerario_idItinerario = itinerario_idItinerario;
        Equipaje_idEquipaje = equipaje_idEquipaje;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                ", email='" + email + '\'' +
                ", categoria='" + categoria + '\'' +
                ", clase='" + clase + '\'' +
                ", tipo='" + tipo + '\'' +
                ", Itinerario_idItinerario=" + Itinerario_idItinerario +
                ", Equipaje_idEquipaje=" + Equipaje_idEquipaje +
                '}';
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

    public void setItinerario_idItinerario(int itinerario_idItinerario) {
        Itinerario_idItinerario = itinerario_idItinerario;
    }

    public void setEquipaje_idEquipaje(int equipaje_idEquipaje) {
        Equipaje_idEquipaje = equipaje_idEquipaje;
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

    public int getItinerario_idItinerario() {
        return Itinerario_idItinerario;
    }

    public int getEquipaje_idEquipaje() {
        return Equipaje_idEquipaje;
    }
}
