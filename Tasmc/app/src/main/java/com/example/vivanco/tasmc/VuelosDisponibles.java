package com.example.vivanco.tasmc;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;


public class VuelosDisponibles extends ActionBarActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vuelos_disponibles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_vuelo);
        setSupportActionBar(toolbar);

        //Habilita el boton para ir a la actividad principal en el Toolbar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        ImageView iconClase = new ImageView(this);
        iconClase.setImageResource(R.drawable.ic_action_important);
        ImageView iconPrecio = new ImageView(this);
        iconPrecio.setImageResource(R.drawable.precio);
        //Subopciones
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        //Background de presionado para los sub botones
        itemBuilder.setBackgroundDrawable(getResources().getDrawable(R.drawable.selector_sub_button_gray));
        SubActionButton btnFecha = itemBuilder.setContentView(iconFecha).build();
        SubActionButton btnClase = itemBuilder.setContentView(iconClase).build();
        SubActionButton btnPrecio = itemBuilder.setContentView(iconPrecio).build();

        //Action al presionar
        btnFecha.setOnClickListener(this);
        btnClase.setOnClickListener(this);
        btnPrecio.setOnClickListener(this);

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(btnFecha)
                .addSubActionView(btnClase)
                .addSubActionView(btnPrecio)
                .attachTo(actionButton)
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vuelos_disponibles, menu);
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

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onRefresh() {

    }
}
