package com.example.vivanco.tasmc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by VIVANCO on 29/04/2015.
 */
public class AdaptadorSalidasInternacionales extends RecyclerView.Adapter<AdaptadorSalidasInternacionales.MyViewHolder> {

    private LayoutInflater inflater;
    List<Vuelo> listSalidas = Collections.emptyList();
    private Context context;

    public AdaptadorSalidasInternacionales(Context context, List<Vuelo> listSalidas) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.listSalidas = listSalidas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.renglon_salidas_inter, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Vuelo currentSalida = listSalidas.get(position);
        holder.destino.setText(currentSalida.getDestino());
        holder.aerolinea.setText(currentSalida.getAerolinea());
        holder.vuelo.setText(currentSalida.getNumero());
        holder.hora.setText(currentSalida.getSalida());
        holder.estado.setText(currentSalida.getEstado());
        holder.sala.setText(currentSalida.getSala());
        holder.terminal.setText(currentSalida.getTerminal());

    }

    @Override
    public int getItemCount() {
        return listSalidas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView destino;
        private TextView aerolinea;
        private TextView vuelo;
        private TextView hora;
        private TextView estado;
        private TextView sala;
        private TextView terminal;

        public MyViewHolder(View itemView) {
            super(itemView);
            destino = (TextView) itemView.findViewById(R.id.tvDestino);
            aerolinea = (TextView) itemView.findViewById(R.id.tvAerolinea);
            vuelo = (TextView) itemView.findViewById(R.id.tvNumero);
            hora = (TextView) itemView.findViewById(R.id.tvSalida);
            estado = (TextView) itemView.findViewById(R.id.tvLlegada);
            sala = (TextView) itemView.findViewById(R.id.tvSala);
            terminal = (TextView) itemView.findViewById(R.id.tvTerminal);

            //icon.setOnClickListener(this); solo con el icono reacciona al click
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

        }
    }
}