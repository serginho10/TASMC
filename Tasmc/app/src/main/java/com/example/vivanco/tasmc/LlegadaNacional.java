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


/**
 * A simple {@link Fragment} subclass.
 */
public class LlegadaNacional extends Fragment {


    private RecyclerView listNac;
    private ArrayList<Vuelo> Vuelos = new ArrayList<>();
    private AdaptadorLlegadasNacionales adaptadorLlegadasNacionales;

    public LlegadaNacional() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        InfoVuelo actividad = (InfoVuelo) getActivity();
        View rootView = inflater.inflate(R.layout.fragment_llegada_nacional, container, false);
        listNac = (RecyclerView) rootView.findViewById(R.id.listLlegadasNac);
        adaptadorLlegadasNacionales = new AdaptadorLlegadasNacionales(getActivity(), actividad.llegNacionales);
        listNac.setAdapter(adaptadorLlegadasNacionales);
        listNac.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }

}
