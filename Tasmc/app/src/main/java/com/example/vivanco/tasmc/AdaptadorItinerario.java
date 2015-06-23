package com.example.vivanco.tasmc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdaptadorItinerario extends RecyclerView.Adapter<AdaptadorItinerario.MyViewHolder> {
    //Lista de itinerarios
    private ArrayList<Itinerario> listItinerario;
    private int itemLayout;
    OnItemClickListener mItemClickListener;

    public AdaptadorItinerario(ArrayList<Itinerario> datosItinerarios) {
        this.listItinerario = datosItinerarios;
        //this.itemLayout=itemLayout;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.renglon_cardview_itinerario, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Itinerario currentItinerario = listItinerario.get(position);
        holder.imagen.setImageResource(R.drawable.iconoitinerario);
        holder.viaje.setText(currentItinerario.getDestino());
        holder.actividades.setText(currentItinerario.toStringActividades());
    }

    @Override
    public int getItemCount() {
        return listItinerario.size();
    }

    public interface OnItemClickListener {
        public void onItemClick(View view , int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imagen;
        private TextView viaje;
        private TextView actividades;

        public MyViewHolder(View itemView) {
            super(itemView);
            imagen = (ImageView) itemView.findViewById(R.id.imagen);
            viaje = (TextView) itemView.findViewById(R.id.viaje);
            actividades = (TextView) itemView.findViewById(R.id.etActividad);

            //icon.setOnClickListener(this); solo con el icono reacciona al click
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getPosition());
            }
        }
    }
}
