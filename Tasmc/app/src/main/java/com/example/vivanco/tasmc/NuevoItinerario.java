package com.example.vivanco.tasmc;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NuevoItinerario extends ActionBarActivity {
    List<String> actividades = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_itinerario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_itin);
        setSupportActionBar(toolbar);

        //Habilita el boton para ir a la actividad principal en el Toolbar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Context context = this;
        boolean extras = false;
        final int id = getIntent().getIntExtra("id",0);
        if(id != 0)
            extras = true;

        final EditText destino = (EditText) findViewById(R.id.etDestino);
        final EditText nuevaActividad = (EditText) findViewById(R.id.etActividad);
        Button btnAgregar = (Button) findViewById(R.id.btnAgregaActividad);
        final Button btnGuardar = (Button) findViewById(R.id.btnGuardaItinerario);
        final ListView lvActividades = (ListView) findViewById(R.id.lvActividades);

        if(extras){
            destino.setText(getIntent().getStringExtra("destino"));
            btnGuardar.setEnabled(false);
            actividades = getIntent().getStringArrayListExtra("actividades");
            ActividadItinerarioAdaptador adaptador = new ActividadItinerarioAdaptador(getApplicationContext(),(ArrayList<String>)actividades);
            lvActividades.setAdapter(adaptador);
        }

        final boolean finalExtras = extras;

        destino.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (finalExtras)
                    btnGuardar.setEnabled(true);
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nuevaActividad.getText().length() == 0)
                    nuevaActividad.setError("Debes escribir la actividad.");
                else {
                    if (finalExtras) {
                        btnGuardar.setEnabled(true);
                    }
                    actividades.add(nuevaActividad.getText().toString());
                    ActividadItinerarioAdaptador adaptador = new ActividadItinerarioAdaptador(getApplicationContext(),(ArrayList<String>)actividades);
                    lvActividades.setAdapter(adaptador);
                    nuevaActividad.setText("");
                }
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(destino.getText().length() == 0)
                    destino.setError("Debes escribir tu destino..");
                else{
                    ManejadorBD bd = new ManejadorBD(getApplicationContext());
                    ArrayList<Actividad> act = new ArrayList<Actividad>();
                    for(int i = 0;i < actividades.size();i++){
                        act.add(new Actividad(i+1,actividades.get(i),5));
                    }
                    if(finalExtras){
                        bd.actualizarItinerario(new Itinerario(id,destino.getText().toString(),act));
                    }else{
                        bd.guardarItinerario(new Itinerario(5,destino.getText().toString(),act));
                    }
                    finish();
                    startActivity(new Intent(getApplicationContext(), Itinerario.class));
                }
            }
        });

        SwipeListViewTouchListener touchListener =new SwipeListViewTouchListener(lvActividades,new SwipeListViewTouchListener.OnSwipeCallback() {
            @Override
            public void onSwipeLeft(ListView listView, int [] reverseSortedPositions) {
                final int[] rev = reverseSortedPositions;
                new AlertDialog.Builder(context)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Eliminando Actividad")
                        .setMessage("¿Estás seguro de eliminar la actividad?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                actividades.remove(rev[0]);
                                ActividadItinerarioAdaptador adaptador = new ActividadItinerarioAdaptador(getApplicationContext(),(ArrayList<String>)actividades);
                                lvActividades.setAdapter(adaptador);
                                btnGuardar.setEnabled(true);
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
            }
            @Override
            public void onSwipeRight(ListView listView, int [] reverseSortedPositions) {
                final int[] rev = reverseSortedPositions;
                new AlertDialog.Builder(context)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Eliminando Actividad")
                        .setMessage("¿Estás seguro de eliminar la actividad?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                actividades.remove(rev[0]);
                                ActividadItinerarioAdaptador adaptador = new ActividadItinerarioAdaptador(getApplicationContext(),(ArrayList<String>)actividades);
                                lvActividades.setAdapter(adaptador);
                                btnGuardar.setEnabled(true);
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        },true, false);

        lvActividades.setOnTouchListener(touchListener);
        lvActividades.setOnScrollListener(touchListener.makeScrollListener());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nuevo_itinerario, menu);
        return true;
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
        //Si el id seleccionado es igual al del home regresa a la principal
        if (id == android.R.id.home) {
            finish();
            startActivity(new Intent(getApplicationContext(),Itinerario.class));
        }


        return super.onOptionsItemSelected(item);
    }
}
