package com.example.vivanco.tasmc;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class ListVuelosBuscados extends ActionBarActivity{

    RecyclerView list;
    private AdaptadorVuelosDisponibles adaptadorVuelosDisponibles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_vuelos);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_vuelo);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        String origen = bundle.getString("origen");
        String destino = bundle.getString("destino");
        String fecha = bundle.getString("fecha");
        String clase = bundle.getString("clase");

        setTitle("Vuelos el "+fecha);

        JSONParser json = new JSONParser(this,getApplicationContext());
        try{
            json.readAndParseJSON("V","",new Object[]{origen, destino, fecha, clase});
        }catch (JSONException e){
            e.printStackTrace();
        }
        while (json.thread.isAlive()){}
        for (int i = 0; i < json.vuelos.size(); i++){
            System.out.println(json.vuelos.get(i).toString());
        }

        list = (RecyclerView) findViewById(R.id.listVuelo);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        list.setLayoutManager(layoutManager);
        adaptadorVuelosDisponibles = new AdaptadorVuelosDisponibles(getApplicationContext(), json.vuelos);
        list.setAdapter(adaptadorVuelosDisponibles);
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
