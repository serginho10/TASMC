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
public class AdaptadorLlegadasInternacionales extends RecyclerView.Adapter<AdaptadorLlegadasInternacionales.MyViewHolder> {

    private LayoutInflater inflater;
    List<Llegadas> listLlegadas = Collections.emptyList();
    private Context context;

    public AdaptadorLlegadasInternacionales(Context context, List<Llegadas> listLlegadas) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.listLlegadas = listLlegadas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.renglon_llegadas_inter, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Llegadas currentLlegada = listLlegadas.get(position);
        holder.origen.setText(currentLlegada.getOrigen());
        holder.aerolinea.setText(currentLlegada.getAerolinea());
        holder.vuelo.setText(currentLlegada.getVuelo());
        holder.hora.setText(currentLlegada.getHora());
        holder.estado.setText(currentLlegada.getEstado());
        holder.sala.setText(currentLlegada.getSala());
        holder.terminal.setText(currentLlegada.getTerminal());

    }

    @Override
    public int getItemCount() {
        return listLlegadas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView origen;
        private TextView aerolinea;
        private TextView vuelo;
        private TextView hora;
        private TextView estado;
        private TextView sala;
        private TextView terminal;

        public MyViewHolder(View itemView) {
            super(itemView);
            origen = (TextView) itemView.findViewById(R.id.origen);
            aerolinea = (TextView) itemView.findViewById(R.id.aerolinea);
            vuelo = (TextView) itemView.findViewById(R.id.vuelo);
            hora = (TextView) itemView.findViewById(R.id.hora);
            estado = (TextView) itemView.findViewById(R.id.estado);
            sala = (TextView) itemView.findViewById(R.id.sala);
            terminal = (TextView) itemView.findViewById(R.id.terminal);

            //icon.setOnClickListener(this); solo con el icono reacciona al click
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

        }
    }
}