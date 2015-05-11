package com.example.vivanco.tasmc;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by VIVANCO on 11/05/2015.
 */
public class AdaptadorLocalVuelo extends RecyclerView.Adapter<AdaptadorLocalVuelo.MyViewHolder> {
    //Lista de itinerarios
    private ArrayList<LocalVuelo> listVuelos;


    public AdaptadorLocalVuelo(ArrayList<LocalVuelo> datosVuelos) {
        this.listVuelos = datosVuelos;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.renglon_vuelos_disponibles, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        LocalVuelo currentVuelo = listVuelos.get(position);
        holder.precio.setText(currentVuelo.getPrecio());
        holder.fechai.setText(currentVuelo.getFecha());
        holder.logoAiri.setImageResource(currentVuelo.getAerolinea());
        holder.origeni.setText(currentVuelo.getFecha());
        holder.horaSalidai.setText(currentVuelo.getFecha());
        holder.destinoi.setText(currentVuelo.getFecha());
        holder.horaLlegadai.setText(currentVuelo.getFecha());
        holder.tiempoi.setText(currentVuelo.getFecha());
        holder.escalasi.setText(currentVuelo.getFecha());


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

        private TextView fechav;
        private ImageView logoAirv;
        private TextView origenv;
        private TextView horaSalidav;
        private TextView destinov;
        private TextView horaLlegadav;
        private TextView tiempov;
        private TextView escalasv;


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

            //vuelta
            fechav = (TextView) itemView.findViewById(R.id.fechav);
            logoAirv = (ImageView) itemView.findViewById(R.id.logoAirv);
            origenv = (TextView) itemView.findViewById(R.id.origenv);
            horaSalidav = (TextView) itemView.findViewById(R.id.horaSalidav);
            destinov = (TextView) itemView.findViewById(R.id.destinov);
            horaLlegadav = (TextView) itemView.findViewById(R.id.horaLlegadav);
            tiempov = (TextView) itemView.findViewById(R.id.tiempov);
            escalasv = (TextView) itemView.findViewById(R.id.escalasv);


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
