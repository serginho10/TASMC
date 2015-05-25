package com.example.vivanco.tasmc;

/**
 * Created by VIVANCO on 23/01/2015.
 */
public class Actividad {

    private int idActividad;
    private String nombre;
    private int itinerario;

    public Actividad(int idActividad, String nombre, int itinerario) {
        this.idActividad = idActividad;
        this.nombre = nombre;
        this.itinerario = itinerario;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getItinerario() {
        return itinerario;
    }

    public void setItinerario(int itinerario) {
        this.itinerario = itinerario;
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "idActividad=" + idActividad +
                ", nombre='" + nombre + '\'' +
                ", itinerario=" + itinerario +
                '}';
    }
}