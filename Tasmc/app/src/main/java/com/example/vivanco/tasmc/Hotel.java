package com.example.vivanco.tasmc;

/**
 * Created by VIVANCO on 22/04/2015.
 */
public class Hotel {

    private long id;
    private String urlImagen;
    private String nombre;
    private String precio;
    private int estrellas;

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", urlImagen='" + urlImagen + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio='" + precio + '\'' +
                ", estrellas=" + estrellas +
                '}';
    }

    public Hotel(long id, String urlImagen, String nombre, String precio, int estrellas) {
        this.id = id;
        this.urlImagen = urlImagen;
        this.nombre = nombre;
        this.precio = precio;
        this.estrellas = estrellas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }
}