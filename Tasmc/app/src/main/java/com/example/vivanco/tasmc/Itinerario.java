package com.example.vivanco.tasmc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;

import java.util.ArrayList;


public class Itinerario extends ActionBarActivity implements View.OnClickListener {

    private static RecyclerView recyclerView;
    private RecyclerView listItinerarios;
    private ArrayList<Actividad> itinerarios;
    private AdaptadorItinerario adaptadorIti;
    private static ArrayList<Integer> removedItems;
    private static final String TAG_NUEVO = "NUEVO";
    private static final String TAG_BORRA = "BORRA";
    private static final String TAG_EDITA = "EDITA";
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerario);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_it);
        setSupportActionBar(toolbar);

        //Habilita el boton para ir a la actividad principal en el Toolbar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Inicializamos con el adaptador de hoteles
        listItinerarios = (RecyclerView) findViewById(R.id.listItinerarios);
        listItinerarios.setLayoutManager(new LinearLayoutManager(this));
        listItinerarios.setHasFixedSize(true);
        listItinerarios.setItemAnimator(new DefaultItemAnimator());
        itinerarios = new ArrayList<Actividad>();
        for (int i = 0; i < MyData.viajes.length; i++) {
            itinerarios.add(new Actividad(
                    MyData.id[i],
                    MyData.imagen[i],
                    MyData.viajes[i],
                    MyData.actividades[i]
            ));
        }

        removedItems = new ArrayList<Integer>();

        adaptadorIti = new AdaptadorItinerario(itinerarios);
        listItinerarios.setAdapter(adaptadorIti);
        buildFAB();

    }

    private void buildFAB() {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_action_new);

        //Creacion del boton flotante
        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(imageView)
                .setBackgroundDrawable(R.drawable.selector_button_red)
                .build();

        actionButton.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_itinerario, menu);
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

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, NuevoItinerario.class);
        startActivity(intent);
        //context.startActivity(new Intent(context, NuevoItinerario.class));
        //removeItem(v);
    }

    private void removeItem(View v) {
        int selectedItemPosition = recyclerView.getChildPosition(v);
        RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForPosition(selectedItemPosition);
        TextView textViewName = (TextView) viewHolder.itemView.findViewById(R.id.viaje);
        String selectedName = (String) textViewName.getText();
        int selectedItemId = -1;
        for (int i = 0; i < MyData.viajes.length; i++) {
            if (selectedName.equals(MyData.viajes[i])) {
                selectedItemId = MyData.id[i];
            }
        }
        removedItems.add(selectedItemId);
        itinerarios.remove(selectedItemPosition);
        adaptadorIti.notifyItemRemoved(selectedItemPosition);
    }

    private void addRemovedItemToList() {
        int addItemAtListPosition = 3;
        itinerarios.add(addItemAtListPosition, new Actividad(
                MyData.id[removedItems.get(0)],
                MyData.imagen[removedItems.get(0)],
                MyData.viajes[removedItems.get(0)],
                MyData.actividades[removedItems.get(0)]));
        adaptadorIti.notifyItemInserted(addItemAtListPosition);
        removedItems.remove(0);
    }

    //Funcion para guardar itinerario
    public void save() {

    }

    //Funcion para borrar itinerario
    public void borra() {

    }

    //Funcion para contraseña de itinerario
    public void lock() {

    }

}
