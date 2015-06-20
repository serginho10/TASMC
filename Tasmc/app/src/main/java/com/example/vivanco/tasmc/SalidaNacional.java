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
        InfoVuelo actividad = (InfoVuelo) getActivity();
        adaptadorSalidasNacionales = new AdaptadorSalidasNacionales(getActivity(), actividad.salNacionales);
        listNac.setAdapter(adaptadorSalidasNacionales);
        listNac.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }

}