package com.example.vivanco.tasmc;

import java.util.Date;

/**
 * Created by VIVANCO on 23/01/2015.
 */
public class Actividad {

    private int id;
    private int imagen;
    private String viaje;
    private String actividades;

    public Actividad(int id, int imagen, String viaje, String actividades) {
        this.id = id;
        this.imagen = imagen;
        this.viaje = viaje;
        this.actividades = actividades;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getViaje() {
        return viaje;
    }

    public void setViaje(String viaje) {
        this.viaje = viaje;
    }

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "id=" + id +
                ", imagen=" + imagen +
                ", viaje='" + viaje + '\'' +
                ", actividades='" + actividades + '\'' +
                '}';
    }
}