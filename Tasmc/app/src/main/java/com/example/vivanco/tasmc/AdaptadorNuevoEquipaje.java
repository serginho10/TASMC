package com.example.vivanco.tasmc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by VIVANCO on 29/04/2015.
 */
public class AdaptadorNuevoEquipaje extends RecyclerView.Adapter<AdaptadorNuevoEquipaje.MyViewHolder> {

    private LayoutInflater inflater;
    List<RenglonCheck> listObjetos = Collections.emptyList();
    private Context context;

    public AdaptadorNuevoEquipaje(Context context, List<RenglonCheck> listObjetos) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.listObjetos = listObjetos;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.renglon_check, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final RenglonCheck currentRenglon = listObjetos.get(position);
        holder.nombre.setText(currentRenglon.getObjeto());
        holder.checkBox.setTag(listObjetos.get(position));
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //asegura que se modifica la Row originalmente asociado a este checkbox
                //para evitar que al reciclar la vista se reinicie el row que antes se mostraba en esta
                //fila. Es imprescindible tagear el Row antes de establecer el valor del checkbox
                if (currentRenglon.getObjeto().equals(buttonView.getTag().toString())) {
                    currentRenglon.setChecked(isChecked);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listObjetos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nombre;
        private CheckBox checkBox;

        public MyViewHolder(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.textViewTitle);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            //icon.setOnClickListener(this); solo con el icono reacciona al click
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

        }
    }
}

