package com.example.vivanco.tasmc;

/**
 * Created by VIVANCO on 17/03/2015.
 */
public class Usuario {
    private int id;
    private String email;
    private String categoria;
    private String clase;

    public Usuario(int id, String email, String categoria, String clase) {
        this.id = id;
        this.email = email;
        this.categoria = categoria;
        this.clase = clase;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", categoria='" + categoria + '\'' +
                ", clase='" + clase + '\'' +
                '}';
    }
}
