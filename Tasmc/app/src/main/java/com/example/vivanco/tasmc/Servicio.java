package com.example.vivanco.tasmc;

/**
 * Created by VIVANCO on 23/03/2015.
 */
public class Servicio {
    private int id;
    private String nombre;
    private String giro;

    public Servicio(int id, String nombre, String giro) {
        this.id = id;
        this.nombre = nombre;
        this.giro = giro;
    }

    @Override
    public String
    toString() {
        return "Servicio{" +
                "id=" + id +
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
