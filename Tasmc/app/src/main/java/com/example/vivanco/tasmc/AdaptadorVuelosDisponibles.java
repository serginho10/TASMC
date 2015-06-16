package com.example.vivanco.tasmc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class AdaptadorVuelosDisponibles extends RecyclerView.Adapter<AdaptadorVuelosDisponibles.MyViewHolder> {

    private LayoutInflater inflater;
    List<Vuelo> list = Collections.emptyList();
    private Context context;

    public AdaptadorVuelosDisponibles(Context context, List<Vuelo> list) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.renglon_vuelos, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Vuelo currentLlegada = list.get(position);
        holder.origen.setText(currentLlegada.getOrigen());
        holder.destino.setText(currentLlegada.getDestino());
        holder.aerolinea.setText(currentLlegada.getAerolinea());
        holder.vuelo.setText(currentLlegada.getNumero());
        holder.salida.setText(currentLlegada.getSalida());
        holder.llegada.setText(currentLlegada.getLlegada());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView origen;
        private TextView destino;
        private TextView aerolinea;
        private TextView vuelo;
        private TextView salida;
        private TextView llegada;

        public MyViewHolder(View itemView) {
            super(itemView);
            origen = (TextView) itemView.findViewById(R.id.tvOrigen);
            aerolinea = (TextView) itemView.findViewById(R.id.tvAerolinea);
            vuelo = (TextView) itemView.findViewById(R.id.tvNumero);
            destino = (TextView) itemView.findViewById(R.id.tvDestino);
            salida = (TextView) itemView.findViewById(R.id.tvSalida);
            llegada = (TextView) itemView.findViewById(R.id.tvLlegada);

            //icon.setOnClickListener(this); solo con el icono reacciona al click
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

        }
    }
}