package com.example.vivanco.tasmc;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Toast;

import com.github.brnunes.swipeablerecyclerview.SwipeableRecyclerViewTouchListener;
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
    private int idItinerario;
    private String destino;
    private String actividades;
    private TextView viaje;
    private TextView actividad;


    public Itinerario(int idItinerario, String destino, String actividades) {
        this.idItinerario = idItinerario;
        this.destino = destino;
        this.actividades = actividades;
    }

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getIdItinerario() {
        return idItinerario;
    }

    public void setIdItinerario(int idItinerario) {
        this.idItinerario = idItinerario;
    }

    @Override
    public String toString() {
        return "Itinerario{" +
                "idItinerario=" + idItinerario +
                ", destino='" + destino + '\'' +
                ", actividades='" + actividades + '\'' +
                '}';
    }

    public Itinerario(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerario);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_it);
        setSupportActionBar(toolbar);

        //Habilita el boton para ir a la actividad principal en el Toolbar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viaje=(TextView)findViewById(R.id.viaje);
        actividad=(TextView)findViewById(R.id.actividades);
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

        adaptadorIti = new AdaptadorItinerario(itinerarios);
        listItinerarios.setAdapter(adaptadorIti);

        SwipeableRecyclerViewTouchListener swipeTouchListener =
                new SwipeableRecyclerViewTouchListener(listItinerarios,
                        new SwipeableRecyclerViewTouchListener.SwipeListener() {
                            @Override
                            public boolean canSwipe(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    itinerarios.remove(position);
                                    adaptadorIti.notifyItemRemoved(position);
                                }
                                adaptadorIti.notifyDataSetChanged();
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    itinerarios.remove(position);
                                    adaptadorIti.notifyItemRemoved(position);
                                }
                                adaptadorIti.notifyDataSetChanged();
                            }
                        });

        listItinerarios.addOnItemTouchListener(swipeTouchListener);
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


    //Funcion para guardar itinerario
    public void save() {

    }

    //Funcion para borrar itinerario
    public void borra() {

    }


}
