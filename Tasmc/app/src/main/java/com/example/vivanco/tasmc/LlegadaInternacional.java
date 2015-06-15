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
public class LlegadaInternacional extends Fragment {


    private RecyclerView listInter;
    private ArrayList<Vuelo> Vuelos = new ArrayList<>();
    private AdaptadorLlegadasInternacionales adaptadorLlegadasInternacionales;

    public LlegadaInternacional() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_llegada_internacional, container, false);
        listInter = (RecyclerView) rootView.findViewById(R.id.listLlegadasInter);
        InfoVuelo actividad = (InfoVuelo) getActivity();
        adaptadorLlegadasInternacionales = new AdaptadorLlegadasInternacionales(getActivity(), actividad.llegInternacionales);
        listInter.setAdapter(adaptadorLlegadasInternacionales);
        listInter.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }

}
