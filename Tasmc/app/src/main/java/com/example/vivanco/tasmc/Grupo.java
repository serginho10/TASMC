package com.example.vivanco.tasmc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ISC_SERGIO on 15/05/15.
 */
public class Grupo {
    public ArrayList<Objeto> objetos;
    public ArrayList<String> seleccion;
    public String nombre;

    public Grupo() {
        objetos = new ArrayList<Objeto>();
        seleccion = new ArrayList<String>();
    }

    public Grupo(String nombre){
        this();
        this.nombre = nombre;
    }

    public String toString(){
        return this.nombre;
    }
}
