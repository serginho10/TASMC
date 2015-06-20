package com.example.vivanco.tasmc;

/**
 * Created by ISC_SERGIO on 02/06/15.
 */
public class Habitacion {

    private long idHabitacion;
    private String tipo;
    private int personas;
    private int precio;
    private long idHotel;

    public Habitacion(long idHabitacion, String tipo, int personas, int precio, long idHotel) {
        this.idHabitacion = idHabitacion;
        this.tipo = tipo;
        this.personas = personas;
        this.precio = precio;
        this.idHotel = idHotel;
    }

    public long getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(long idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPersonas() {
        return personas;
    }

    public void setPersonas(int personas) {
        this.personas = personas;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public long getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(long idHotel) {
        this.idHotel = idHotel;
    }
}
