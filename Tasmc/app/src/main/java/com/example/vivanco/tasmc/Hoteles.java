package com.example.vivanco.tasmc;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


public class Hoteles extends ActionBarActivity implements View.OnClickListener {

    TextView habitaciones;
    TextView huespedes;
    Button masHues;
    Button menosHues;
    Button masHab;
    Button menosHab;
    Spinner categorias;
    String[] opcatego = new String[]{"1 estrella", "2 estrellas", "3 estrellas", "4 estrellas", "5 estrellas"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoteles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_hotel);
        setSupportActionBar(toolbar);

        //Habilita el boton para ir a la actividad principal en el Toolbar

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        habitaciones = (TextView) findViewById(R.id.habitaciones);
        huespedes = (TextView) findViewById(R.id.huespedes);
        masHab = (Button) findViewById(R.id.masHab);
        menosHab = (Button) findViewById(R.id.menosHab);
        masHues = (Button) findViewById(R.id.masHues);
        menosHues = (Button) findViewById(R.id.menosHues);
        //spinner hotel estrellas
        categorias = (Spinner) findViewById(R.id.categoria);
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcatego);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorias.setAdapter(dataAdapter1);

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
}
