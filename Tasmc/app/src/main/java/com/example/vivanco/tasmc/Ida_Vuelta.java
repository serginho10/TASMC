package com.example.vivanco.tasmc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Ida_Vuelta extends Fragment {
    Spinner clases;
    String[] opclase = new String[]{"Económico", "Económico Premium", "Business", "Primera"};
    Spinner pasajeros;
    String[] opasa = new String[]{"1 Pasajero", "2 Pasajeros", "3 Pasajeros", "4 Pasajeros","5 Pasajeros"};


    public Ida_Vuelta() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ida__vuelta, container, false);
        clases = (Spinner) rootView.findViewById(R.id.clase);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, opclase);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clases.setAdapter(dataAdapter);

        pasajeros = (Spinner) rootView.findViewById(R.id.pasajeros);
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, opasa);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pasajeros.setAdapter(dataAdapter1);
        EditText fechaI = (EditText) rootView.findViewById(R.id.fechaI);
        EditText fechaV = (EditText) rootView.findViewById(R.id.fechaV);
        fechaI.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    DateDialog dialog = new DateDialog(v);
                    //FragmentTransaction ft = getFragmentManager().beginTransaction();
                    //dialog.show(ft, "DatePicker");
                }
            }
        });
        fechaV.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    DateDialog dialog = new DateDialog(v);
                    //FragmentTransaction ft = getFragmentManager().beginTransaction();
                    //dialog.show(ft, "DatePicker");
                }
            }
        });
        return rootView;

    }

}
