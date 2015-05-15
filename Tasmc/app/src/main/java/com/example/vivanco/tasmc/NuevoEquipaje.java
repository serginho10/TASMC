package com.example.vivanco.tasmc;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;


public class NuevoEquipaje extends ActionBarActivity {

    private RecyclerView listObjetos;
    private ArrayList<RenglonCheck> objetos;
    private AdaptadorNuevoEquipaje adaptadorNuevoEquipaje;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_equipaje);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_equi);
        setSupportActionBar(toolbar);

        //Habilita el boton para ir a la actividad principal en el Toolbar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listObjetos = (RecyclerView) findViewById(R.id.listObjetos);
        AdaptadorNuevoEquipaje adaptadorNuevoEquipaje = new AdaptadorNuevoEquipaje(this, getDatos());
        listObjetos.setAdapter(adaptadorNuevoEquipaje);
        listObjetos.setLayoutManager(new LinearLayoutManager(this));

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

    //metodo para anadir la informacion
    public ArrayList<RenglonCheck> getDatos() {
        ArrayList<RenglonCheck> datos = new ArrayList<>();
        String[] objetos;
        //Recuperamos los string-array
        Resources res = getResources();
        objetos = res.getStringArray(R.array.objetos);
        for (int i = 0; i < objetos.length; i++) {
            RenglonCheck current = new RenglonCheck();
            current.setObjeto(objetos[i]);
            datos.add(current);
        }
        return datos;
    }
}
