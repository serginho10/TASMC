package com.example.vivanco.tasmc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Ida extends Fragment {
    Spinner clases;
    String[] opclase = new String[]{"Económico", "Económico Premium", "Business", "Primera"};
    Spinner pasajeros;
    String[] opasa = new String[]{"1 Pasajero", "2 Pasajeros", "3 Pasajeros", "4 Pasajeros", "5 Pasajeros"};
    EditText origeni;
    EditText destinoi;

    public Ida() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ida, container, false);
        clases = (Spinner) rootView.findViewById(R.id.clasei);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, opclase);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clases.setAdapter(dataAdapter);

        pasajeros = (Spinner) rootView.findViewById(R.id.pasajerosi);
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, opasa);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pasajeros.setAdapter(dataAdapter1);
        final EditText fechaida = (EditText) rootView.findViewById(R.id.fechaida);
        origeni = (EditText) rootView.findViewById(R.id.origeni);
        destinoi = (EditText) rootView.findViewById(R.id.destinoi);

        fechaida.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    DateDialog dialog = new DateDialog(v);
                    //FragmentTransaction ft = getFragmentManager().beginTransaction();
                    //dialog.show(ft, "DatePicker");
                }
            }
        });

        View.OnClickListener listnr = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), VuelosDisponibles.class);
                intent.putExtra("origeni", origeni.getText().toString());
                intent.putExtra("destinoi", destinoi.getText().toString());
                intent.putExtra("fechaida", fechaida.getText().toString());
                intent.putExtra("pasajerosi", pasajeros.getSelectedItem().toString());
                intent.putExtra("clasei", clases.getSelectedItem().toString());
                startActivity(intent);
            }
        };
        Button vuelos = (Button) rootView.findViewById(R.id.vuelosi);
        vuelos.setOnClickListener(listnr);
        return rootView;
    }

}

