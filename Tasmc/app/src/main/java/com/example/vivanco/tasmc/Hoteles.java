package com.example.vivanco.tasmc;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;


public class Hoteles extends ActionBarActivity implements View.OnClickListener{

    TextView habitaciones;
    TextView huespedes;
    Button masHues;
    Button menosHues;
    Button masHab;
    Button menosHab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoteles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_hotel);
        setSupportActionBar(toolbar);

        //Habilita el boton para ir a la actividad principal en el Toolbar

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        habitaciones=(TextView)findViewById(R.id.habitaciones);
        huespedes=(TextView)findViewById(R.id.huespedes);
        masHab=(Button)findViewById(R.id.masHab);
        menosHab=(Button)findViewById(R.id.menosHab);
        masHues=(Button)findViewById(R.id.masHues);
        menosHues=(Button)findViewById(R.id.menosHues);

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
