package com.example.vivanco.tasmc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by VIVANCO on 12/01/2015.
 */
public class AdaptadorItinerario extends RecyclerView.Adapter<AdaptadorItinerario.MyViewHolder> {
    //Lista de itinerarios
    private ArrayList<Actividad> listItinerarios;


    public AdaptadorItinerario(ArrayList<Actividad> datosItinerarios) {
        this.listItinerarios=datosItinerarios;

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.renglon_cardview_itinerario, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Actividad currentItinerario = listItinerarios.get(position);
        holder.imagen.setImageResource(currentItinerario.getImagen());
        holder.viaje.setText(currentItinerario.getViaje());
        holder.actividades.setText(currentItinerario.getActividades());
    }

    @Override
    public int getItemCount() {
        return listItinerarios.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imagen;
        private TextView viaje;
        private TextView actividades;

        public MyViewHolder(View itemView) {
            super(itemView);
            imagen = (ImageView) itemView.findViewById(R.id.imagen);
            viaje = (TextView) itemView.findViewById(R.id.viaje);
            actividades = (TextView) itemView.findViewById(R.id.actividades);


            //icon.setOnClickListener(this); solo con el icono reacciona al click
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            //Toast.makeText(context, "hola"+getPosition(),Toast.LENGTH_LONG); usa get position
            switch (getPosition() + 1) {
/*                case 1:
                    context.startActivity(new Intent(context, Hoteles.class));
                    break;*/

            }
        }
    }
}
