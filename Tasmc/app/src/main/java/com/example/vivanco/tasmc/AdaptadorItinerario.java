package com.example.vivanco.tasmc;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by VIVANCO on 25/01/2015.
 */
public class AdaptadorItinerario extends RecyclerView.Adapter<AdaptadorItinerario.ItinerarioViewHolder> {

    private List<Actividad> actList;

    public AdaptadorItinerario(List<Actividad> actList) {
        this.actList = actList;
    }

    @Override
    public void onBindViewHolder(ItinerarioViewHolder holder, int position) {

        Actividad a = actList.get(position);
        holder.icon.setImageResource(a.iconIti);
        holder.titulo.setText(a.titit);
        holder.activi.setText(a.activ);
        holder.viaje.setText(a.viaji);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public ItinerarioViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.renglon_card_iti, viewGroup, false);

        return new ItinerarioViewHolder(itemView);
    }

    public static class ItinerarioViewHolder extends RecyclerView.ViewHolder {

        protected ImageView icon;
        protected TextView titulo;
        protected TextView activi;
        protected TextView viaje;

        public ItinerarioViewHolder(View v) {
            super(v);
            icon =  (ImageView) v.findViewById(R.id.logi);
            titulo = (TextView)  v.findViewById(R.id.titi);
            activi = (TextView)  v.findViewById(R.id.acti);
            viaje = (TextView) v.findViewById(R.id.viaje);
        }
    }
}
