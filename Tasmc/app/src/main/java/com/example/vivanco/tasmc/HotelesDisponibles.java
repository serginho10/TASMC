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


public class HotelesDisponibles extends ActionBarActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private static RecyclerView recyclerView;
    private RecyclerView listHoteles;
    //private ArrayList<Hotel> hoteles=new ArrayList<>();
    private ArrayList<LocalHotel> hoteles;
    private ImageLoader imageLoader;
    private VolleySingleton volleySingleton;
    private AdaptadorLocalHotel adaptadorHoteles;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private static ArrayList<Integer> removedItems;
    private TextView textPesos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoteles_disponibles);
        //Inicializamos con el adaptador de hoteles
        listHoteles = (RecyclerView) findViewById(R.id.listHoteles);
        listHoteles.setLayoutManager(new LinearLayoutManager(this));
        listHoteles.setHasFixedSize(true);
        listHoteles.setItemAnimator(new DefaultItemAnimator());
        hoteles = new ArrayList<LocalHotel>();
        for (int i = 0; i < HotelData.nombre.length; i++) {
            hoteles.add(new LocalHotel(
                    HotelData.id[i],
                    HotelData.imagen[i],
                    HotelData.nombre[i],
                    HotelData.estrellas[i],
                    HotelData.precio[i]
            ));
        }
        removedItems = new ArrayList<Integer>();

        adaptadorHoteles = new AdaptadorLocalHotel(hoteles);
        listHoteles.setAdapter(adaptadorHoteles);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeHoteles);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_hotel);
        setSupportActionBar(toolbar);
        textPesos = (TextView) findViewById(R.id.pesos);


        //Habilita el boton para ir a la actividad principal en el Toolbar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

        //Creacion de las opciones del boton flotante

        ImageView iconNuevo = new ImageView(this);
        iconNuevo.setImageResource(R.drawable.ic_action_calendar);
        ImageView iconEstrellas = new ImageView(this);
        iconEstrellas.setImageResource(R.drawable.ic_action_important);
        ImageView iconPrecio = new ImageView(this);
        iconPrecio.setImageResource(R.drawable.precio);
        //Subopciones
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        //Background de presionado para los sub botones
        itemBuilder.setBackgroundDrawable(getResources().getDrawable(R.drawable.selector_sub_button_gray));
        SubActionButton btnNuevo = itemBuilder.setContentView(iconNuevo).build();
        SubActionButton btnEstrellas = itemBuilder.setContentView(iconEstrellas).build();
        SubActionButton btnPrecio = itemBuilder.setContentView(iconPrecio).build();

        //Action al presionar
        btnNuevo.setOnClickListener(this);
        btnEstrellas.setOnClickListener(this);
        btnPrecio.setOnClickListener(this);

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(btnNuevo)
                .addSubActionView(btnEstrellas)
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
