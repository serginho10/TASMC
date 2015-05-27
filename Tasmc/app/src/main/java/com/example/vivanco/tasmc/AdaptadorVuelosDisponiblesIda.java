package com.example.vivanco.tasmc;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by VIVANCO on 27/05/2015.
 */
public class AdaptadorVuelosDisponiblesIda extends RecyclerView.Adapter<AdaptadorVuelosDisponiblesIda.MyViewHolder> {
    //Lista de itinerarios
    private ArrayList<LocalVueloIda> listVuelos;


    public AdaptadorVuelosDisponiblesIda(ArrayList<LocalVueloIda> datosVuelos) {
        this.listVuelos = datosVuelos;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.renglon_vuelos_disponiblesida, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        LocalVueloIda currentVuelo = listVuelos.get(position);
        holder.precio.setText(currentVuelo.getPrecio());
        holder.fechai.setText(currentVuelo.getFechai());
        holder.logoAiri.setImageResource(currentVuelo.getAerolineai());
        holder.origeni.setText(currentVuelo.getOrigeni());
        holder.horaSalidai.setText(currentVuelo.getHoraSalidai());
        holder.destinoi.setText(currentVuelo.getDestinoi());
        holder.horaLlegadai.setText(currentVuelo.getHoraLlegadai());
        holder.tiempoi.setText(currentVuelo.getTiempoi());
        holder.escalasi.setText(currentVuelo.getEscalasi());

    }

    @Override
    public int getItemCount() {
        return listVuelos.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView precio;
        private TextView fechai;
        private ImageView logoAiri;
        private TextView origeni;
        private TextView horaSalidai;
        private TextView destinoi;
        private TextView horaLlegadai;
        private TextView tiempoi;
        private TextView escalasi;


        public MyViewHolder(View itemView) {
            super(itemView);
            //general
            precio = (TextView) itemView.findViewById(R.id.precio);
            //ida
            fechai = (TextView) itemView.findViewById(R.id.fechai);
            logoAiri = (ImageView) itemView.findViewById(R.id.logoAiri);
            origeni = (TextView) itemView.findViewById(R.id.origeni);
            horaSalidai = (TextView) itemView.findViewById(R.id.horaSalidai);
            destinoi = (TextView) itemView.findViewById(R.id.destinoi);
            horaLlegadai = (TextView) itemView.findViewById(R.id.horaLlegadai);
            tiempoi = (TextView) itemView.findViewById(R.id.tiempoi);
            escalasi = (TextView) itemView.findViewById(R.id.escalasi);

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
