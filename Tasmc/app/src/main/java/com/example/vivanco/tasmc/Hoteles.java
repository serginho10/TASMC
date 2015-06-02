package com.example.vivanco.tasmc;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


public class Hoteles extends ActionBarActivity implements View.OnClickListener {

    Spinner huespedes;
    Spinner categorias;
    EditText lugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoteles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_hotel);
        setSupportActionBar(toolbar);

        lugar = (EditText) findViewById(R.id.lugar);

        //Habilita el boton para ir a la actividad principal en el Toolbar

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String[] array;

        //spinner huespedes
        array = getResources().getStringArray(R.array.personas);
        huespedes = (Spinner) findViewById(R.id.huespedes);
        ArrayAdapter dataAdapter = new ArrayAdapter(this, R.layout.spinner_text_layout ,array);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        huespedes.setAdapter(dataAdapter);

        //spinner hotel estrellas
        array = getResources().getStringArray(R.array.estrellas);
        categorias = (Spinner) findViewById(R.id.categoria);
        ArrayAdapter dataAdapter2 = new ArrayAdapter(this, R.layout.spinner_text_layout ,array);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorias.setAdapter(dataAdapter2);

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

    @Override
    public void onClick(View v) {

    }

    public void buscar(View view) {
        Intent intent = new Intent(this, HotelesDisponibles.class);
        //Obtenemos los valores para pasarlos a la siguiente actividad
        intent.putExtra("lugar", lugar.getText().toString());
        intent.putExtra("huespedes", huespedes.getSelectedItem().toString());
        intent.putExtra("estrellas", categorias.getSelectedItem().toString());
        startActivity(intent);
    }
}
