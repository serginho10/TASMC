package com.example.vivanco.tasmc;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;


public class InfoMiVuelo extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_mi_vuelo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_infv);
        setSupportActionBar(toolbar);

        //Habilita el boton para ir a la actividad principal en el Toolbar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        String[] datos = bundle.getStringArray("vuelo");

        TextView tvNumero = (TextView) findViewById(R.id.tvNumero);
        TextView tvOrigen = (TextView) findViewById(R.id.tvOrigen);
        TextView tvDestino = (TextView) findViewById(R.id.tvDestino);
        TextView tvSalida = (TextView) findViewById(R.id.tvSalida);
        TextView tvLlegada = (TextView) findViewById(R.id.tvLlegada);
        TextView tvSala = (TextView) findViewById(R.id.tvSala);
        TextView tvTerminal = (TextView) findViewById(R.id.tvTerminal);
        TextView tvAerolinea = (TextView) findViewById(R.id.tvAerolinea);
        TextView tvEstado = (TextView) findViewById(R.id.tvEstado);

        tvAerolinea.setText(datos[0]);
        tvOrigen.setText(datos[1]);
        tvDestino.setText(datos[2]);
        tvEstado.setText(datos[3]);
        tvLlegada.setText(datos[4]);
        tvSalida.setText(datos[5]);
        tvSala.setText(datos[6]);
        tvNumero.setText(datos[7]);
        tvTerminal.setText(datos[8]);

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
