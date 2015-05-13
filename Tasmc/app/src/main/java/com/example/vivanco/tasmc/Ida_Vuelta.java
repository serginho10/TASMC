package com.example.vivanco.tasmc;

import android.app.FragmentTransaction;
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

public class Ida_Vuelta extends Fragment implements View.OnClickListener{
    Spinner clases;
    Spinner pasajeros;
    EditText origeniv;
    EditText destinoiv;
    EditText fechaidaiv;
    EditText fechavueltaiv;


    public Ida_Vuelta() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ida__vuelta, container, false);
        clases = (Spinner) rootView.findViewById(R.id.claseiv);
        ArrayAdapter dataAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.clases,android.R.layout.simple_spinner_item);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clases.setAdapter(dataAdapter);

        pasajeros = (Spinner) rootView.findViewById(R.id.pasajerosiv);
        ArrayAdapter dataAdapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.pasajeros,android.R.layout.simple_spinner_item);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pasajeros.setAdapter(dataAdapter1);

        final EditText fechaI = (EditText) rootView.findViewById(R.id.fechaI);
        final EditText fechaV = (EditText) rootView.findViewById(R.id.fechaV);

        origeniv = (EditText) rootView.findViewById(R.id.origeniv);
        destinoiv = (EditText) rootView.findViewById(R.id.destinoiv);

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

        View.OnClickListener listnr=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),VuelosDisponibles.class);
                intent.putExtra("origeniv", origeniv.getText().toString());
                intent.putExtra("destinoiv", destinoiv.getText().toString());
                intent.putExtra("fechaI", fechaI.getText().toString());
                intent.putExtra("fechaV", fechaV.getText().toString());
                intent.putExtra("pasajerosiv", pasajeros.getSelectedItem().toString());
                intent.putExtra("claseiv", clases.getSelectedItem().toString());
                startActivity(intent);
            }
        };
        Button vuelos=(Button)rootView.findViewById(R.id.vuelos);
        vuelos.setOnClickListener(listnr);

        return rootView;

    }


    @Override
    public void onClick(View v) {

    }
}
