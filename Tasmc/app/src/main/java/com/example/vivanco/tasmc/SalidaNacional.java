package com.example.vivanco.tasmc;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class SalidaNacional extends Fragment {

    private RecyclerView listNac;
    private ArrayList<Vuelo> Vuelos = new ArrayList<>();
    private AdaptadorSalidasNacionales adaptadorSalidasNacionales;

    public SalidaNacional() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_salida_nacional, container, false);
        listNac = (RecyclerView) rootView.findViewById(R.id.listSalidasNac);
        adaptadorSalidasNacionales = new AdaptadorSalidasNacionales(getActivity(), getDatos());
        listNac.setAdapter(adaptadorSalidasNacionales);
        listNac.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }


    //metodo para añadir la informacion
    public static List<Salidas> getDatos() {
        List<Salidas> datos = new ArrayList<>();
        String[] destinos = {"Miami", "Las Vegas", "New York", "Houston"};
        String[] aerolineas = {"Interjet", "Volaris", "Aeromexico", "Interjet"};
        String[] vuelos = {"AIJ3455", "VOI9584", "AMX4593", "AIJ4343"};
        String[] horas = {"10:00", "9:45", "8:15", "7:15"};
        String[] estados = {"Despego", "Despego", "Despego", "Despego"};
        String[] salas = {"21", "34", "56", "29"};
        String[] terminales = {"T1", "T1", "T2", "T1"};


        for (int i = 0; i < destinos.length && i < aerolineas.length && i < vuelos.length && i < horas.length
                && i < estados.length && i < salas.length && i < terminales.length; i++) {
            Salidas current = new Salidas();
            current.setDestino(destinos[i]);
            current.setAerolinea(aerolineas[i]);
            current.setVuelo(vuelos[i]);
            current.setHora(horas[i]);
            current.setEstado(estados[i]);
            current.setTerminal(terminales[i]);

            datos.add(current);
        }
        return datos;
    }

}