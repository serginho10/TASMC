package com.example.vivanco.tasmc;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by VIVANCO on 11/05/2015.
 */
public class AdaptadorLocalHotel extends RecyclerView.Adapter<AdaptadorLocalHotel.MyViewHolder> {
//Lista de itinerarios
private ArrayList<LocalHotel> listHoteles;


public AdaptadorLocalHotel(ArrayList<LocalHotel> datosHoteles) {
        this.listHoteles=datosHoteles;

        }
@Override
public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.renglon_hoteles_disponibles, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
        }

@Override
public void onBindViewHolder(final MyViewHolder holder, int position) {
        LocalHotel currentHotel = listHoteles.get(position);
        holder.imagen.setImageResource(currentHotel.getImagen());
        holder.nombre.setText(currentHotel.getNombre());
        holder.estrellas.setRating(currentHotel.getEstrellas());
        holder.precio.setText(currentHotel.getPrecio());

}

@Override
public int getItemCount() {
        return listHoteles.size();
        }

static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ImageView imagen;
    private TextView nombre;
    private RatingBar estrellas;
    private TextView precio;

    public MyViewHolder(View itemView) {
        super(itemView);
        imagen = (ImageView) itemView.findViewById(R.id.imagen);
        nombre = (TextView) itemView.findViewById(R.id.nombre);
        estrellas = (RatingBar) itemView.findViewById(R.id.estrellas);
        precio = (TextView) itemView.findViewById(R.id.precio);


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
