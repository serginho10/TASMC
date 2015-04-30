package com.example.vivanco.tasmc;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;



public class ListServicios extends ActionBarActivity {
    String[] nombres;
    String[] giros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_servicios);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_infoa);
        setSupportActionBar(toolbar);

        //Habilita el boton para ir a la actividad principal en el Toolbar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Recuperamos los string-array
        Resources res=getResources();
        nombres=res.getStringArray(R.array.nombre);
        giros=res.getStringArray(R.array.giro);

        //Recuperar el listview
        ListView listView = (ListView) findViewById(R.id.listser);
        //Iniciamos el adaptador el cual llamara
        ServicioAdaptador adaptador = new ServicioAdaptador(this, nombres, giros);
        //Asociar el adaptador a la vista
        listView.setAdapter(adaptador);
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
