package com.example.vivanco.tasmc;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ISC_SERGIO on 15/05/15.
 */
public class EquipajeSeleccionado extends ActionBarActivity {
    SparseArray<Grupo> grupos = new SparseArray<Grupo>();
    ManejadorBD bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listaseleccionada);

        bd = new ManejadorBD(getApplicationContext());

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_equi);
        setSupportActionBar(toolbar);

        //Habilita el boton para ir a la actividad principal en el Toolbar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        String equipaje = bundle.getString("equipaje");

        obtieneDatos(equipaje);
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
        ListaExpandibleAdapter adapter = new ListaExpandibleAdapter(this,
                grupos);
        listView.setAdapter(adapter);
    }

    public void obtieneDatos(String equipaje) {
        Objeto[] obj = bd.obtenerObjetosDe(equipaje);
        Map<String,Grupo> gru = new HashMap<String,Grupo>();
        Grupo group;
        for (int j = 0; j < obj.length; j++) {
            Grupo g = gru.get(obj[j].getCategoria());
            if(g == null){
                group = new Grupo(obj[j].getCategoria());
                group.children.add(obj[j].getNombre());
                gru.put(obj[j].getCategoria(),group);
            }else{
                g.children.add(obj[j].getNombre());
                gru.put(obj[j].getCategoria(),g);
            }
        }
        int j = 0;
        Iterator it = gru.keySet().iterator();
        while(it.hasNext()){
            String key = (String) it.next();
            grupos.append(j, gru.get(key));
        }
    }
}
