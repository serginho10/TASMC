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
    Spinner habitaciones;
    Spinner categorias;
    EditText lugar;
    EditText fechain;
    EditText fechaout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoteles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_hotel);
        setSupportActionBar(toolbar);

        lugar = (EditText) findViewById(R.id.lugar);
        fechain = (EditText) findViewById(R.id.fechain);
        fechaout = (EditText) findViewById(R.id.fechaout);

        //Habilita el boton para ir a la actividad principal en el Toolbar

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //spinner huespedes
        huespedes = (Spinner) findViewById(R.id.huespedes);
        ArrayAdapter dataAdapter = ArrayAdapter.createFromResource(this, R.array.personas, android.R.layout.simple_spinner_item);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        huespedes.setAdapter(dataAdapter);

        //spinner habitaciones
        habitaciones = (Spinner) findViewById(R.id.habitaciones);
        ArrayAdapter dataAdapter1 = ArrayAdapter.createFromResource(this, R.array.habitaciones, android.R.layout.simple_spinner_item);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        habitaciones.setAdapter(dataAdapter1);

        //spinner hotel estrellas
        categorias = (Spinner) findViewById(R.id.categoria);
        ArrayAdapter dataAdapter2 = ArrayAdapter.createFromResource(this, R.array.estrellas, android.R.layout.simple_spinner_item);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorias.setAdapter(dataAdapter2);

    }

    public void onStart() {
        super.onStart();

        fechain.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    DateDialog dialog = new DateDialog(v);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");
                }
            }
        });
        fechaout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    DateDialog dialog = new DateDialog(v);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");
                }
            }
        });

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
        intent.putExtra("checkin", fechain.getText().toString());
        intent.putExtra("checkout", fechaout.getText().toString());
        intent.putExtra("habitaciones", habitaciones.getSelectedItem().toString());
        intent.putExtra("huespedes", huespedes.getSelectedItem().toString());
        intent.putExtra("estrellas", categorias.getSelectedItem().toString());
        startActivity(intent);
    }
}
