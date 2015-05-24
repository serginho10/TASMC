package com.example.vivanco.tasmc;

import java.util.Comparator;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ISC_SERGIO on 15/05/15.
 */
public class EquipajeSeleccionado extends ActionBarActivity {
    ArrayList<Grupo> grupos = new ArrayList<Grupo>();
    ManejadorBD bd;
    String equipaje;

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
        equipaje = bundle.getString("equipaje");
        setTitle(equipaje);
        obtieneDatos(equipaje);
        final ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
        ListaExpandibleAdapter adapter = new ListaExpandibleAdapter(this,
                grupos,listView);
        adapter.setEquipaje(equipaje);
        listView.setAdapter(adapter);
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                CheckedTextView checkbox = (CheckedTextView) v.findViewById(R.id.list_item_text_child);
                checkbox.toggle();

                View parentView = listView.findViewWithTag(grupos.get(groupPosition).nombre);
                if(parentView != null){
                    TextView sub = (TextView) parentView.findViewById(R.id.list_item_text_subscriptions);
                    if(sub != null){
                        Grupo grupo = grupos.get(groupPosition);
                        if(checkbox.isChecked()){
                            SharedPreferences settings = getSharedPreferences(equipaje, 0);
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putBoolean(checkbox.getText().toString(), true);
                            editor.commit();
                            grupo.seleccion.add(checkbox.getText().toString());
                            Collections.sort(grupo.seleccion, new CustomComparator());
                        }else{
                            SharedPreferences settings = getSharedPreferences(equipaje, 0);
                            SharedPreferences.Editor editor = settings.edit();
                            editor.remove(checkbox.getText().toString()).commit();
                            grupo.seleccion.remove(checkbox.getText().toString());
                        }
                        sub.setText(grupo.seleccion.toString());
                    }
                }
                return true;
            }
        });
    }

    public class CustomComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    public void obtieneDatos(String equipaje) {
        Objeto[] obj = bd.obtenerObjetosDe(equipaje);
        Map<String,Grupo> gru = new HashMap<String,Grupo>();
        Grupo group;
        for (int j = 0; j < obj.length; j++) {
            Grupo g = gru.get(obj[j].getCategoria());
            if(g == null){
                group = new Grupo(obj[j].getCategoria());
                group.objetos.add(obj[j]);
                group.nombre = obj[j].getCategoria();
                gru.put(obj[j].getCategoria(),group);
            }else{
                g.objetos.add(obj[j]);
                g.nombre = obj[j].getCategoria();
                gru.put(obj[j].getCategoria(),g);
            }
        }
        int j = 0;
        Iterator it = gru.keySet().iterator();
        while(it.hasNext()){
            String key = (String) it.next();
            grupos.add(gru.get(key));
            j++;
        }
    }
}
