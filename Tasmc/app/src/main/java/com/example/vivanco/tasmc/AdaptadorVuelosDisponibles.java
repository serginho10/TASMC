package com.example.vivanco.tasmc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;

/**
 * Created by VIVANCO on 05/05/2015.
 */
public class AdaptadorVuelosDisponibles extends RecyclerView.Adapter<AdaptadorVuelosDisponibles.MyViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    //Lista de vuelos
    private ArrayList<Vuelo> listVuelos = new ArrayList<>();
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;


    public AdaptadorVuelosDisponibles(Context context) {
        inflater = LayoutInflater.from(context);
        volleySingleton = VolleySingleton.getInstance();
        imageLoader = volleySingleton.getImageLoader();
    }

    public void setVueloList(ArrayList<Vuelo> listVuelos) {
        this.listVuelos = listVuelos;
        notifyItemRangeChanged(0, listVuelos.size());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.renglon_vuelos_disponibles, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Vuelo currentVuelo = listVuelos.get(position);

        holder.precio.setText(currentVuelo.getPrecio());

        holder.ida.setText(currentVuelo.getVuelo());
        holder.origeni.setText(currentVuelo.getOrigen());
        holder.horaSalidai.setText(currentVuelo.getHoraSalida());
        holder.horaLlegadai.setText(currentVuelo.getHoraLlegada());
        holder.tiempoi.setText(currentVuelo.getEscalas());
        holder.escalasi.setText(currentVuelo.getTiempo());
        holder.destinoi.setText(currentVuelo.getDestino());
        holder.fechai.setText(currentVuelo.getFechaSalida());

        holder.vuelta.setText(currentVuelo.getVuelo());
        holder.origenv.setText(currentVuelo.getOrigen());
        holder.horaSalidav.setText(currentVuelo.getHoraSalida());
        holder.horaLlegadav.setText(currentVuelo.getHoraLlegada());
        holder.tiempov.setText(currentVuelo.getEscalas());
        holder.escalasv.setText(currentVuelo.getTiempo());
        holder.destinov.setText(currentVuelo.getDestino());
        holder.fechav.setText(currentVuelo.getFechaSalida());

        String urlImagen = currentVuelo.getAerolinea();
        if (urlImagen != null) {
            imageLoader.get(urlImagen, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    holder.logoAiri.setImageBitmap(response.getBitmap());
                    holder.logoAirv.setImageBitmap(response.getBitmap());
                }


                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return listVuelos.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //Elementos card
        private TextView tarifa;
        private TextView precio;
        private TextView cargos;

        //Elementos ida
        private ImageView logoAiri;
        private TextView ida;
        private TextView origeni;
        private TextView horaSalidai;
        private TextView totali;
        private TextView tiempoi;
        private TextView escalasi;
        private TextView destinoi;
        private TextView horaLlegadai;
        private TextView fechai;

        //Elementos vuelta
        private ImageView logoAirv;
        private TextView vuelta;
        private TextView origenv;
        private TextView horaSalidav;
        private TextView totalv;
        private TextView tiempov;
        private TextView escalasv;
        private TextView destinov;
        private TextView horaLlegadav;
        private TextView fechav;

        public MyViewHolder(View itemView) {
            super(itemView);
            tarifa = (TextView) itemView.findViewById(R.id.tarifa);
            precio = (TextView) itemView.findViewById(R.id.precio);
            cargos = (TextView) itemView.findViewById(R.id.cargos);


            logoAiri = (ImageView) itemView.findViewById(R.id.logoAiri);
            ida = (TextView) itemView.findViewById(R.id.ida);
            origeni = (TextView) itemView.findViewById(R.id.origeni);
            horaSalidai = (TextView) itemView.findViewById(R.id.horaSalidai);
            totali = (TextView) itemView.findViewById(R.id.totali);
            tiempoi = (TextView) itemView.findViewById(R.id.tiempoi);
            escalasi = (TextView) itemView.findViewById(R.id.escalasi);
            destinoi = (TextView) itemView.findViewById(R.id.destinoi);
            horaLlegadai = (TextView) itemView.findViewById(R.id.horaLlegadai);
            fechai = (TextView) itemView.findViewById(R.id.fechai);

            logoAirv = (ImageView) itemView.findViewById(R.id.logoAirv);
            vuelta = (TextView) itemView.findViewById(R.id.vuelta);
            origenv = (TextView) itemView.findViewById(R.id.origenv);
            horaSalidav = (TextView) itemView.findViewById(R.id.horaSalidav);
            totalv = (TextView) itemView.findViewById(R.id.totalv);
            tiempov = (TextView) itemView.findViewById(R.id.tiempov);
            escalasv = (TextView) itemView.findViewById(R.id.escalasv);
            destinov = (TextView) itemView.findViewById(R.id.destinov);
            horaLlegadav = (TextView) itemView.findViewById(R.id.horaLlegadav);
            fechav = (TextView) itemView.findViewById(R.id.fechav);

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
//            context.startActivity(new Intent(context, SubActivity.class));
        }
    }
}
