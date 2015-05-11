package com.example.vivanco.tasmc;

/**
 * Created by VIVANCO on 11/05/2015.
 */
public class LocalHotel {
    private int id;
    private int imagen;
    private String nombre;
    private int estrellas;
    private String precio;

    public LocalHotel(int id, int imagen, String nombre, int estrellas, String precio) {
        this.id = id;
        this.imagen = imagen;
        this.nombre = nombre;
        this.estrellas = estrellas;
        this.precio = precio;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "LocalHotel{" +
                "id=" + id +
                ", imagen=" + imagen +
                ", nombre='" + nombre + '\'' +
                ", estrellas=" + estrellas +
                ", precio='" + precio + '\'' +
                '}';
    }
}
