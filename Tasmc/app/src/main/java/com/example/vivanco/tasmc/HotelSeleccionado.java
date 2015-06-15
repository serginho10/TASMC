package com.example.vivanco.tasmc;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by ISC_SERGIO on 14/06/15.
 */
public class HotelSeleccionado extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_hotel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_infv);
        setSupportActionBar(toolbar);

        //Habilita el boton para ir a la actividad principal en el Toolbar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*for (int j = 0; j < json.hoteles.size(); j++) {
            if(json.hoteles.get(j).getNombre().compareTo(list.getItemAtPosition(position).toString()) == 0){
                i.putExtra("categoria", json.hoteles.get(j).getCategoria());
                i.putExtra("web", json.hoteles.get(j).getWeb());
                i.putExtra("telefono", json.hoteles.get(j).getTelefono());
                i.putExtra("nohabitaciones", json.hoteles.get(j).getNoHabitaciones());
                i.putExtra("ciudad", json.hoteles.get(j).getCiudad());
                i.putExtra("habitaciones", json.hoteles.get(j).getHabitaciones().size());
                ArrayList<Habitacion> habitaciones = json.hoteles.get(j).getHabitaciones();
                for(int k = 0; k < habitaciones.size(); k++){
                    i.putExtra("tipohabitacion"+(k+1), habitaciones.get(k).getTipo());
                    i.putExtra("personashabitacion"+(k+1), habitaciones.get(k).getPersonas());
                    i.putExtra("preciohabitacion"+(k+1), habitaciones.get(k).getPrecio());
                }
                j = json.hoteles.size();
            }
        }*/

        Bundle bundle = getIntent().getExtras();
        setTitle(bundle.getString("hotel"));

        TextView tvCategoria = (TextView) findViewById(R.id.tvCategoria);
        TextView tvWeb = (TextView) findViewById(R.id.tvWeb);
        TextView tvTelefono = (TextView) findViewById(R.id.tvTelefono);
        TextView tvCiudad = (TextView) findViewById(R.id.tvCiudad);
//        Spinner sHabitaciones = (Spinner) findViewById(R.id.sHabitaciones);

        tvCategoria.setText(bundle.getInt("categoria")+" estrellas");
        tvWeb.setText(bundle.getString("web"));
        tvTelefono.setText(bundle.getString("telefono"));
        tvCiudad.setText(bundle.getString("ciudad"));
        //sHabitaciones.setText(datos[5]);

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
