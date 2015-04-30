package com.example.vivanco.tasmc;

/**
 * Created by VIVANCO on 23/03/2015.
 */
public class Servicio {
    private int id;
    private String categoria;
    private String nombre;
    private String giro;

    public Servicio(int id, String categoria, String nombre, String giro) {
        this.id = id;
        this.categoria = categoria;
        this.nombre = nombre;
        this.giro = giro;
    }

    @Override
    public String toString() {
        return "Servicio{" +
                "id=" + id +
                ", categoria='" + categoria + '\'' +
                ", nombre='" + nombre + '\'' +
                ", giro='" + giro + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGiro() {
        return giro;
    }

    public void setGiro(String giro) {
        this.giro = giro;
    }
}