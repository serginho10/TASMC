package com.example.vivanco.tasmc;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class NuevoItinerario extends ActionBarActivity {
    private EditText destino;
    private EditText actividades;
    private Button guardar;
    private ManejadorBD db;
    private Itinerario itinerario = null;
    int[] imageIDs = {
            R.drawable.viaje,
            R.drawable.viaje1,
            R.drawable.viaje2,
            R.drawable.viaje3,
            R.drawable.viaje4,
            R.drawable.viaje5,
            R.drawable.viaje6,
            R.drawable.viaje7
    };
    int nextImageIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_itinerario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_itin);
        setSupportActionBar(toolbar);

        //Habilita el boton para ir a la actividad principal en el Toolbar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//quitamos texto de toolbar

        destino = (EditText) findViewById(R.id.destino);
        actividades = (EditText) findViewById(R.id.actividades);
        guardar = (Button) findViewById(R.id.guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new ManejadorBD(v.getContext());
                int imageId = imageIDs[nextImageIndex];
                nextImageIndex = (nextImageIndex + 1) % imageIDs.length;

                itinerario = new Itinerario(0, nextImageIndex, destino.getText().toString(), actividades.getText().toString());
                db.guardarItinerario(itinerario);

                Toast.makeText(v.getContext(), "Itinerario guardado correctamente",
                        Toast.LENGTH_LONG).show();
                db.closeDatabase();
                finish();
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
