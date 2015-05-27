package com.example.vivanco.tasmc;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.ArrayList;


public class VuelosDisponiblesIda extends ActionBarActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView listVuelos;
    private ArrayList<LocalVueloIda> vuelos;
    private ImageLoader imageLoader;
    private VolleySingleton volleySingleton;
    private AdaptadorVuelosDisponiblesIda adaptadorVuelosDisponiblesIda;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView pesos;
    private TextView tarifa;
    private TextView cargos;
    private ImageView avioni;
    private TextView ida;
    private ImageView dividerIda;
    private TextView totali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vuelos_disponibles_ida);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_vuelo);
        setSupportActionBar(toolbar);

        //Habilita el boton para ir a la actividad principal en el Toolbar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Inicializamos con el adaptador de hoteles
        listVuelos = (RecyclerView) findViewById(R.id.listVuelos);
        listVuelos.setLayoutManager(new LinearLayoutManager(this));
        listVuelos.setHasFixedSize(true);
        listVuelos.setItemAnimator(new DefaultItemAnimator());
        vuelos = new ArrayList<LocalVueloIda>();
        for (int i = 0; i < VueloDataIda.precio.length; i++) {
            vuelos.add(new LocalVueloIda(
                    VueloDataIda.id[i],
                    VueloDataIda.precio[i],
                    VueloDataIda.fechai[i],
                    VueloDataIda.logoAiri[i],
                    VueloDataIda.origeni[i],
                    VueloDataIda.horaSalidai[i],
                    VueloDataIda.destinoi[i],
                    VueloDataIda.horaLlegadai[i],
                    VueloDataIda.tiempoi[i],
                    VueloDataIda.escalasi[i]
            ));
        }
        adaptadorVuelosDisponiblesIda = new AdaptadorVuelosDisponiblesIda(vuelos);
        listVuelos.setAdapter(adaptadorVuelosDisponiblesIda);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeVuelos);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        buildFAB();
        pesos = (TextView) findViewById(R.id.pesos);
        tarifa = (TextView) findViewById(R.id.tarifa);
        cargos = (TextView) findViewById(R.id.cargos);
        avioni = (ImageView) findViewById(R.id.avioni);
        ida = (TextView) findViewById(R.id.ida);
        dividerIda = (ImageView) findViewById(R.id.dividerIda);
        totali = (TextView) findViewById(R.id.totali);

    }

    private void buildFAB() {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_action_new);

        //Creacion del boton flotante
        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(imageView)
                .setBackgroundDrawable(R.drawable.selector_button_red)
                .build();

        //Creacion de las opciones del boton flotante

        ImageView iconFecha = new ImageView(this);
        iconFecha.setImageResource(R.drawable.ic_action_calendar);
        ImageView iconPrecio = new ImageView(this);
        iconPrecio.setImageResource(R.drawable.precio);
        //Subopciones
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        //Background de presionado para los sub botones
        itemBuilder.setBackgroundDrawable(getResources().getDrawable(R.drawable.selector_sub_button_gray));
        SubActionButton btnFecha = itemBuilder.setContentView(iconFecha).build();
        SubActionButton btnPrecio = itemBuilder.setContentView(iconPrecio).build();

        //Action al presionar
        btnFecha.setOnClickListener(this);
        btnPrecio.setOnClickListener(this);

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(btnFecha)
                .addSubActionView(btnPrecio)
                .attachTo(actionButton)
                .build();
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

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onRefresh() {

    }
}
