package com.example.vivanco.tasmc;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by VIVANCO on 14/04/2015.
 */
public class ServicioAdaptador extends ArrayAdapter<String>{

    Context context;
    String[] nombreServ;
    String[] giroServ;

    public ServicioAdaptador(Context context, String[] nombres,String[] giros) {
        super(context,R.layout.elementos_list_servicios,R.id.nombre,nombres);
        this.context=context;
        this.nombreServ=nombres;
        this.giroServ=giros;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=inflater.inflate(R.layout.elementos_list_servicios,parent,false);
        TextView nombres=(TextView)row.findViewById(R.id.nombre);
        TextView giros=(TextView)row.findViewById(R.id.giro);
        nombres.setText(nombreServ[position]);
        giros.setText(giroServ[position]);
        return row;
    }
}
