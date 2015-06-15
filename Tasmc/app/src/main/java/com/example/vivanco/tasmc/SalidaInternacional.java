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

public class SalidaInternacional extends Fragment {

    private RecyclerView listInter;
    private ArrayList<Vuelo> Vuelos = new ArrayList<>();
    private AdaptadorSalidasInternacionales adaptadorSalidasInternacionales;

    public SalidaInternacional() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_salida_internacional, container, false);
        listInter = (RecyclerView) rootView.findViewById(R.id.listSalidasInter);
        InfoVuelo actividad = (InfoVuelo) getActivity();
        adaptadorSalidasInternacionales = new AdaptadorSalidasInternacionales(getActivity(), actividad.salInternacionales);
        listInter.setAdapter(adaptadorSalidasInternacionales);
        listInter.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }

}
