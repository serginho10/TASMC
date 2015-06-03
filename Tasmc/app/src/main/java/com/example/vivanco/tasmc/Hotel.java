package com.example.vivanco.tasmc;

import java.util.ArrayList;

public class Hotel {

    private long idHotel;
    private String nombre;
    private int categoria;
    private String web;
    private String telefono;
    private int noHabitaciones;
    private String ciudad;
    private ArrayList<Habitacion> habitaciones;

    @Override
    public String toString() {
        return "Hotel{" +
                "idHotel=" + idHotel +
                ", nombre='" + nombre + '\'' +
                ", categoria=" + categoria +
                ", web='" + web + '\'' +
                ", telefono='" + telefono + '\'' +
                ", noHabitaciones=" + noHabitaciones +
                ", ciudad='" + ciudad + '\'' +
                ", habitaciones=" + habitaciones +
                '}';
    }

    public Hotel(long id,String nombre, int categoria, String web, String telefono, int noHabitaciones, String ciudad, ArrayList<Habitacion> habitaciones) {
        this.idHotel = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.web = web;
        this.telefono = telefono;
        this.noHabitaciones = noHabitaciones;
        this.ciudad = ciudad;
        this.habitaciones = habitaciones;
    }

    public long getId(){
        return idHotel;
    }

    public void setId(long id){
        this.idHotel = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getNoHabitaciones() {
        return noHabitaciones;
    }

    public void setNoHabitaciones(int noHabitaciones) {
        this.noHabitaciones = noHabitaciones;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(ArrayList<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }
}