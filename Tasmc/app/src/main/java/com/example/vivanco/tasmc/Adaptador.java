package com.example.vivanco.tasmc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

/**
 * Created by VIVANCO on 12/01/2015.
 */
public class Adaptador extends RecyclerView.Adapter<Adaptador.MyViewHolder> {
    private LayoutInflater inflater;
    List<Informacion> datos = Collections.emptyList();
    private Context context;

    public Adaptador(Context context, List<Informacion> datos) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.datos = datos;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.renglon_recycler, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Informacion current = datos.get(position);//objeto actual
        holder.titulo.setText(current.titulo);
        holder.icon.setImageResource(current.iconId);

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView titulo;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.listIcon);
            //icon.setOnClickListener(this); solo con el icono reacciona al click
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            //Toast.makeText(context, "hola"+getPosition(),Toast.LENGTH_LONG); usa get position
            switch (getPosition()+1) {
                case 1:
                    context.startActivity(new Intent(context, Hoteles.class));
                    break;

                case 2:
                    context.startActivity(new Intent(context, Vuelos.class));
                    break;

                case 3:
                    context.startActivity(new Intent(context, InfoAicm.class));
                    break;

                case 4:
                    context.startActivity(new Intent(context, ListEquipaje.class));
                    break;

                case 5:
                    context.startActivity(new Intent(context, Itinerario.class));
                    break;

                case 6:
                    context.startActivity(new Intent(context, RutaAicm.class));
                    break;

                case 7:
                    context.startActivity(new Intent(context, Ubicate.class));
                    break;

                case 8:
                    context.startActivity(new Intent(context, InfoVuelo.class));
                    break;

            }
//            context.startActivity(new Intent(context, SubActivity.class));
        }
    }
}
