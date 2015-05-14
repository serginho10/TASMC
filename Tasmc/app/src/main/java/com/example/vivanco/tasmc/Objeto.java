package com.example.vivanco.tasmc;

/**
 * Created by VIVANCO on 17/03/2015.
 */
public class Objeto {

    private int id;
    private String nombre;
    private String categoria;

    @Override
    public String toString() {
        return "Objeto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getId() {

        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public Objeto(int id, String nombre, String categoria) {

        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
    }
}
