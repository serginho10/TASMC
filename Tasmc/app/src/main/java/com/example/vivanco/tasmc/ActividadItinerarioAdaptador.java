package com.example.vivanco.tasmc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ISC_SERGIO on 26/05/15.
 */
public class ActividadItinerarioAdaptador extends ArrayAdapter<String> {
    public ActividadItinerarioAdaptador(Context context, ArrayList<String> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_actividades_itinerario, parent, false);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        tvName.setText(getItem(position));
        return convertView;
    }
}
