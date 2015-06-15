package com.example.vivanco.tasmc;

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

import org.json.JSONException;


public class Hoteles extends ActionBarActivity {

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

        ManejadorBD bd = new ManejadorBD(getApplicationContext());
        String catUsuario = bd.getCategoriaUsuario();

        //spinner hotel estrellas
        array = getResources().getStringArray(R.array.estrellas);
        categorias = (Spinner) findViewById(R.id.categoria);
        ArrayAdapter dataAdapter2 = new ArrayAdapter(this, R.layout.spinner_text_layout ,array);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorias.setAdapter(dataAdapter2);
        if (catUsuario.compareTo("1 estrella") == 0)
            categorias.setSelection(0);
        else if (catUsuario.compareTo("2 estrellas") == 0)
            categorias.setSelection(1);
        else if (catUsuario.compareTo("3 estrellas") == 0)
            categorias.setSelection(2);
        else if (catUsuario.compareTo("4 estrellas") == 0)
            categorias.setSelection(3);
        else if (catUsuario.compareTo("5 estrellas") == 0)
            categorias.setSelection(4);
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

    public void buscar(View view) {
        if(lugar.getText().length() == 0){
            lugar.setError("Debes ingresar la ciudad que visitarás");
        }else{
            Intent intent = new Intent(this, ListHoteles.class);
            intent.putExtra("lugar",lugar.getText().toString().toUpperCase());
            intent.putExtra("huespedes",huespedes.getSelectedItem().toString());
            intent.putExtra("categorias",categorias.getSelectedItem().toString());
            startActivity(intent);
        }
    }
}