package com.example.vivanco.tasmc;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.brnunes.swipeablerecyclerview.SwipeableRecyclerViewTouchListener;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;

import java.util.ArrayList;


public class Itinerario extends ActionBarActivity implements View.OnClickListener {
    private int idItinerario;
    private String destino;
    private ArrayList<Actividad> actividades;

    public Itinerario(int idItinerario, String destino, ArrayList<Actividad> actividades) {
        this.idItinerario = idItinerario;
        this.destino = destino;
        this.actividades = actividades;
    }

    public Itinerario() {
    }

    public int getIdItinerario() {
        return idItinerario;
    }

    public void setIdItinerario(int idItinerario) {
        this.idItinerario = idItinerario;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public ArrayList<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(ArrayList<Actividad> actividades) {
        this.actividades = actividades;
    }

    @Override
    public String toString() {
        return "Itinerario{" +
                "idItinerario=" + idItinerario +
                ", destino='" + destino + '\'' +
                ", actividades=" + actividades +
                '}';
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerario);

        RecyclerView recyclerView;
        RecyclerView listItinerarios;
        final ArrayList<Itinerario> itinerarios;
        final AdaptadorItinerario adaptadorIti;
        ArrayList<Integer> removedItems;
        final String TAG_NUEVO = "NUEVO";
        final String TAG_BORRA = "BORRA";
        final String TAG_EDITA = "EDITA";
        ManejadorBD db;

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_it);
        setSupportActionBar(toolbar);

        //Habilita el boton para ir a la actividad principal en el Toolbar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new ManejadorBD(this);

        listItinerarios = (RecyclerView) findViewById(R.id.listItinerarios);
        listItinerarios.setLayoutManager(new LinearLayoutManager(this));
        listItinerarios.setHasFixedSize(true);
        listItinerarios.setItemAnimator(new DefaultItemAnimator());

        final ManejadorBD bd = new ManejadorBD(getApplicationContext());

        final Context context = this;

        itinerarios = db.obtenerItinerarios();
        adaptadorIti = new AdaptadorItinerario(itinerarios);
        listItinerarios.setAdapter(adaptadorIti);
        //listItinerarios.setAdapter(new AdaptadorItinerario(db.getAllData(), R.layout.renglon_cardview_itinerario));

        SwipeableRecyclerViewTouchListener swipeTouchListener =
                new SwipeableRecyclerViewTouchListener(listItinerarios,
                        new SwipeableRecyclerViewTouchListener.SwipeListener() {
                            @Override
                            public boolean canSwipe(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, final int[] reverseSortedPositions) {
                                final int[] rev = reverseSortedPositions;
                                new AlertDialog.Builder(context)
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .setTitle("Eliminando Itinerario")
                                        .setMessage("¿Estás seguro de eliminar el itinerario?")
                                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                for (int position : rev) {
                                                    bd.borrarItinerario(itinerarios.get(position));
                                                    itinerarios.remove(position);
                                                    adaptadorIti.notifyItemRemoved(position);
                                                }
                                                Toast.makeText(getApplicationContext(), "Itinerario eliminado correctamente",
                                                        Toast.LENGTH_LONG).show();
                                                adaptadorIti.notifyDataSetChanged();
                                            }

                                        })
                                        .setNegativeButton("No", null)
                                        .show();
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    Itinerario iti = itinerarios.get(position);
                                    Intent intent = new Intent(getApplicationContext(), NuevoItinerario.class);
                                    intent.putExtra("destino", iti.getDestino());
                                    intent.putExtra("id", iti.getIdItinerario());
                                    ArrayList<String> acts = new ArrayList<String>();
                                    for (int i = 0; i < iti.getActividades().size(); i++) {
                                        acts.add(iti.getActividades().get(i).getNombre());
                                    }
                                    intent.putStringArrayListExtra("actividades", acts);
                                    finish();
                                    startActivity(intent);
                                }
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
        finish();
        Intent intent = new Intent(this, NuevoItinerario.class);
        startActivity(intent);
        //context.startActivity(new Intent(context, NuevoItinerario.class));
        //removeItem(v);
    }

    public String toStringActividades() {
        String act = "";
        for (int i = 0; i < actividades.size(); i++) {
            if (i == (actividades.size() - 1))
                act += actividades.get(i).getNombre() + ". ";
            else
                act += actividades.get(i).getNombre() + ", ";
        }
        return act;
    }
}