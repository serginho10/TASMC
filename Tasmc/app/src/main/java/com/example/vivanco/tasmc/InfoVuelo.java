package com.example.vivanco.tasmc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;

import java.util.ArrayList;


public class InfoVuelo extends ActionBarActivity implements View.OnClickListener {

    private ViewPager mPager;
    private SlidingTabLayout mTabs;
    private EditText vuelo;
    private Button buscar;
    ArrayList<Vuelo> vuelos;
    ArrayList<Vuelo> llegNacionales = new ArrayList<Vuelo>();
    ArrayList<Vuelo> llegInternacionales = new ArrayList<Vuelo>();
    ArrayList<Vuelo> salNacionales = new ArrayList<Vuelo>();
    ArrayList<Vuelo> salInternacionales = new ArrayList<Vuelo>();
    String[] inter = {"AMSTERDAN","ATLANTA","BUENOS AIRES","BOGOTA","CARACAS","CHARLOTTE","CHICAGO",
            "DALLAS","DETROIT","FRANKFURT","GIG","LOS ANGELES","LA HABANA","LAS VEGAS","LIMA",
            "LONDRES","MADRID","MC ALLEN","MIAMI","MONTREAL","MUNICH","NEW YORK","NEWARK","ORANGE",
            "ORLANDO","PANAMA","PARIS","PHOENIX","QUITO","SAN ANTONIO","SAN FRANCISCO","SAN JOSE",
            "SAN SALVADOR","SACRAMENTO","SALT LAKE","SAN DIEGO","SAN PEDRO","SANTIAGO","SAO PAULO",
            "TOKIO","TORONTO","VANCOUVER"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_vuelo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_infv);
        setSupportActionBar(toolbar);

        //Habilita el boton para ir a la actividad principal en el Toolbar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        JSONParser json = new JSONParser(this,getApplicationContext());
        try{
            json.readAndParseJSON("I","",new Object[]{});
        }catch (JSONException e){
            e.printStackTrace();
        }
        while (json.thread.isAlive()){}
        vuelos = json.vuelos;
        for (int i = 0; i < json.vuelos.size(); i++){
            Vuelo act = json.vuelos.get(i);
            if(isSalidaAICM(act)){
                if(isInter(act.getOrigen()) || isInter(act.getDestino())){
                    salInternacionales.add(act);
                }else{
                    salNacionales.add(act);
                }
            }else{
                if(isInter(act.getOrigen()) || isInter(act.getDestino())){
                    llegInternacionales.add(act);
                }else{
                    llegNacionales.add(act);
                }
            }
        }

        System.out.println(vuelos.size());
        System.out.println(salInternacionales.size());
        System.out.println(salNacionales.size());
        System.out.println(llegInternacionales.size());
        System.out.println(llegNacionales.size());

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mTabs.setDistributeEvenly(true);
        mTabs.setViewPager(mPager);
        vuelo = (EditText) findViewById(R.id.tvNumero);
        buscar = (Button) findViewById(R.id.buscar);
        buscar.setOnClickListener(this);
    }

    public boolean isInter(String pais){
        for (int i = 0; i<inter.length; i++){
            if(inter[i].compareTo(pais) == 0){
                return true;
            }
        }
        return false;
    }

    public boolean isSalidaAICM(Vuelo v){
        if(v.getOrigen().compareTo("MEXICO") == 0){
            return true;
        }else return false;
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
        if(vuelo.getText().toString().length() == 0){
            vuelo.setError("Debes ingresar el número de tu vuelo.");
        }else{
            Intent intent = new Intent(this, InfoMiVuelo.class);
            intent.putExtra("vuelo",buscaVuelo(vuelo.getText().toString()));
            startActivity(intent);
        }
    }

    public String[] buscaVuelo(String vuelo){
        String[] datos = new String[9];
        for(int i = 0; i<vuelos.size(); i++){
            if(vuelos.get(i).getNumero().compareTo(vuelo.toUpperCase()) == 0){
                datos[0] = vuelos.get(i).getAerolinea();
                datos[1] = vuelos.get(i).getOrigen();
                datos[2] = vuelos.get(i).getDestino();
                datos[3] = vuelos.get(i).getEstado();
                datos[4] = vuelos.get(i).getLlegada();
                datos[5] = vuelos.get(i).getSalida();
                datos[6] = vuelos.get(i).getSala();
                datos[7] = vuelos.get(i).getNumero();
                datos[8] = vuelos.get(i).getTerminal();
                i = vuelos.size();
            }
        }
        return datos;
    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        String[] tabs;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            tabs = getResources().getStringArray(R.array.tabv);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (position == 0) {
                fragment = new LlegadaNacional();
            }
            if (position == 1) {
                fragment = new LlegadaInternacional();
            }
            if (position == 2) {
                fragment = new SalidaNacional();
            }
            if (position == 3) {
                fragment = new SalidaInternacional();
            }

            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

    public static class MyFragment extends Fragment {
        public static MyFragment getInstance(int position) {
            MyFragment fragment = new MyFragment();
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View layout = inflater.inflate(R.layout.fragment_llegada_nacional, container, false);
            return layout;
        }
    }

}