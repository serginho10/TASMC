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

/**
 * Created by VIVANCO on 05/05/2015.
 */
public class AdaptadorVuelosDisponibles extends RecyclerView.Adapter<AdaptadorVuelosDisponibles.MyViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    //Lista de hoteles
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

        holder.vuelo.setText(currentVuelo.getVuelo());
        holder.origen.setText(currentVuelo.getOrigen());
        holder.horaSalida.setText(currentVuelo.getHoraSalida());
        holder.horaLlegada.setText(currentVuelo.getHoraLlegada());
        holder.tiempo.setText(currentVuelo.getEscalas());
        holder.escalas.setText(currentVuelo.getTiempo());
        holder.destino.setText(currentVuelo.getDestino());
        holder.fecha.setText(currentVuelo.getFechaSalida());


        String urlImagen = currentVuelo.getAerolinea();
        if (urlImagen != null) {
            imageLoader.get(urlImagen, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    holder.logoAir.setImageBitmap(response.getBitmap());
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

        private ImageView logoAir;
        private TextView vuelo;
        private TextView origen;
        private TextView horaSalida;
        private TextView total;
        private TextView tiempo;
        private TextView escalas;
        private TextView destino;
        private TextView horaLlegada;
        private TextView fecha;

        public MyViewHolder(View itemView) {
            super(itemView);
            logoAir = (ImageView) itemView.findViewById(R.id.logoAir);
            vuelo = (TextView) itemView.findViewById(R.id.vuelo);
            origen = (TextView) itemView.findViewById(R.id.origen);
            horaSalida = (TextView) itemView.findViewById(R.id.horaSalida);
            total = (TextView) itemView.findViewById(R.id.total);
            tiempo = (TextView) itemView.findViewById(R.id.tiempo);
            escalas = (TextView) itemView.findViewById(R.id.escalas);
            destino = (TextView) itemView.findViewById(R.id.destino);
            horaLlegada = (TextView) itemView.findViewById(R.id.horaLlegada);
            fecha = (TextView) itemView.findViewById(R.id.fecha);


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
