package com.example.vivanco.tasmc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
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

        final EditText destino = (EditText) findViewById(R.id.etDestino);
        final EditText nuevaActividad = (EditText) findViewById(R.id.etActividad);
        Button btnAgregar = (Button) findViewById(R.id.btnAgregaActividad);
        Button btnGuardar = (Button) findViewById(R.id.btnGuardaItinerario);
        final ListView lvActividades = (ListView) findViewById(R.id.lvActividades);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nuevaActividad.getText().length() == 0)
                    nuevaActividad.setError("Debes escribir la actividad.");
                else{
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
                    bd.guardarItinerario(new Itinerario(5,destino.getText().toString(),act));
                    finish();
                    startActivity(new Intent(getApplicationContext(),Itinerario.class));
                }
            }
        });
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
            NavUtils.navigateUpFromSameTask(this);
        }


        return super.onOptionsItemSelected(item);
    }
}
