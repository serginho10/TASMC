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


public class VuelosDisponibles extends ActionBarActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView listVuelos;
    private ArrayList<LocalVuelo> vuelos;
    private ImageLoader imageLoader;
    private VolleySingleton volleySingleton;
    private AdaptadorLocalVuelo adaptadorVuelosDisponibles;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView pesos;
    private TextView tarifa;
    private TextView cargos;
    private ImageView avioni;
    private TextView ida;
    private ImageView dividerIda;
    private TextView totali;
    private ImageView avionv;
    private TextView vuelta;
    private ImageView dividerVuelta;
    private TextView totalv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vuelos_disponibles);
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
        vuelos = new ArrayList<LocalVuelo>();
        for (int i = 0; i < VueloData.precio.length; i++) {
            vuelos.add(new LocalVuelo(
                    VueloData.id[i],
                    VueloData.precio[i],
                    VueloData.fechai[i],
                    VueloData.logoAiri[i],
                    VueloData.origeni[i],
                    VueloData.horaSalidai[i],
                    VueloData.destinoi[i],
                    VueloData.horaLlegadai[i],
                    VueloData.tiempoi[i],
                    VueloData.escalasi[i],
                    VueloData.fechav[i],
                    VueloData.logoAirv[i],
                    VueloData.origenv[i],
                    VueloData.horaSalidav[i],
                    VueloData.destinov[i],
                    VueloData.horaLlegadav[i],
                    VueloData.tiempov[i],
                    VueloData.escalasv[i]
            ));
        }
        adaptadorVuelosDisponibles = new AdaptadorLocalVuelo(vuelos);
        listVuelos.setAdapter(adaptadorVuelosDisponibles);
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
        avionv = (ImageView) findViewById(R.id.avionv);
        vuelta = (TextView) findViewById(R.id.vuelta);
        dividerVuelta = (ImageView) findViewById(R.id.dividerVuelta);
        totalv = (TextView) findViewById(R.id.totalv);

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
