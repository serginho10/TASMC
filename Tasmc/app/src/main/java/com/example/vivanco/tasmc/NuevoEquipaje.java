package com.example.vivanco.tasmc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class NuevoEquipaje extends ActionBarActivity {
    ArrayList<Grupo> grupos = new ArrayList<Grupo>();
    ManejadorBD bd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_equipaje);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_equi);
        setSupportActionBar(toolbar);

        //Habilita el boton para ir a la actividad principal en el Toolbar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bd = new ManejadorBD(getApplicationContext());

        obtieneDatos();
        final ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
        ListaExpandibleAdapter adapter = new ListaExpandibleAdapter(this,
                grupos,listView);
        listView.setAdapter(adapter);
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                CheckedTextView checkbox = (CheckedTextView) v.findViewById(R.id.list_item_text_child);
                checkbox.toggle();

                View parentView = listView.findViewWithTag(grupos.get(groupPosition).nombre);
                if (parentView != null) {
                    TextView sub = (TextView) parentView.findViewById(R.id.list_item_text_subscriptions);
                    if (sub != null) {
                        Grupo grupo = grupos.get(groupPosition);
                        if (checkbox.isChecked()) {
                            grupo.seleccion.add(checkbox.getText().toString());
                            Collections.sort(grupo.seleccion, new CustomComparator());
                        } else {
                            grupo.seleccion.remove(checkbox.getText().toString());
                        }
                        sub.setText(grupo.seleccion.toString());
                    }
                }
                return true;
            }
        });

        final EditText etNombreEquipaje = (EditText) findViewById(R.id.etNuevoEquipaje);
        Button btnGuardar = (Button) findViewById(R.id.btnGuardarEquipaje);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreEquipaje = etNombreEquipaje.getText().toString();
                if(nombreEquipaje.length() != 0) {
                    java.util.Date fecha = new Date();
                    String id = fecha.getDay() +""+fecha.getMonth()+""+fecha.getHours()+""+fecha.getMinutes();
                    bd.guardarEquipaje(new Equipaje(Integer.parseInt(id), nombreEquipaje));
                    for(int i = 0; i < grupos.size();i++){
                        ArrayList<String> sel = grupos.get(i).seleccion;
                        for(int j = 0; j < sel.size();j++){
                            bd.guardarEquipajeHasObjeto(new EquipajeHasObjeto(Integer.parseInt(id),bd.getIdObjeto(sel.get(j))));
                        }
                    }
                    finish();
                    startActivity(new Intent(getApplicationContext(), ListEquipaje.class));
                }else etNombreEquipaje.setError( "Debes ingresar un nombre de Equipaje" );
            }
        });

    }

    public class CustomComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    public void obtieneDatos() {
        Objeto[] obj = bd.obtenerObjetos();
        Map<String,Grupo> gru = new HashMap<String,Grupo>();
        Grupo group;
        for (int j = 0; j < obj.length; j++) {
            System.out.println(obj[j].getCategoria()+" ++++++++++++++++++");
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
