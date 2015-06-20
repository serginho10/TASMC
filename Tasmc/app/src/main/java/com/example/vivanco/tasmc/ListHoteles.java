package com.example.vivanco.tasmc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ListHoteles extends ActionBarActivity implements View.OnClickListener{
    ListView list;
    int[] image = {R.drawable.hotel1,R.drawable.hotel2,R.drawable.hotel3,R.drawable.hotel4,R.drawable.hotel5};
    Map<String,Integer> estrellas = new HashMap<String,Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hoteles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_hotel);
        setSupportActionBar(toolbar);

        //Habilita el boton para ir a la actividad principal en el Toolbar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        String lugar = bundle.getString("lugar");
        String huespedes = bundle.getString("huespedes");
        String categorias = bundle.getString("categorias");

        final JSONParser json = new JSONParser(this,getApplicationContext());
        try {
            json.readAndParseJSON("H",lugar, new Object[]{huespedes,categorias});
        } catch (JSONException e) {
            e.printStackTrace();
        }
        while(json.thread.isAlive()){}
        final String[] titulos = new String[json.hoteles.size()];
        final Map<String,String> descripcion = new HashMap<String,String>();
        for(int i = 0; i<json.hoteles.size(); i++){
            titulos[i] = json.hoteles.get(i).getNombre();
            descripcion.put(json.hoteles.get(i).getNombre(),"Teléfono: "+json.hoteles.get(i).getTelefono());
            estrellas.put(json.hoteles.get(i).getNombre(),json.hoteles.get(i).getCategoria());
            System.out.println(json.hoteles.get(i).toString());
        }

        list = (ListView) findViewById(R.id.listEquipaje);
        AdaptadorHoteles adaptadorHoteles = new AdaptadorHoteles(this, titulos, image, descripcion);
        list.setAdapter(adaptadorHoteles);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), HotelSeleccionado.class);
                i.putExtra("hotel", list.getItemAtPosition(position).toString());
                for (int j = 0; j < json.hoteles.size(); j++) {
                    if(json.hoteles.get(j).getNombre().compareTo(list.getItemAtPosition(position).toString()) == 0){
                        i.putExtra("categoria", json.hoteles.get(j).getCategoria());
                        i.putExtra("web", json.hoteles.get(j).getWeb());
                        i.putExtra("telefono", json.hoteles.get(j).getTelefono());
                        i.putExtra("nohabitaciones", json.hoteles.get(j).getNoHabitaciones());
                        i.putExtra("ciudad", json.hoteles.get(j).getCiudad());
                        i.putExtra("habitaciones", json.hoteles.get(j).getHabitaciones().size());
                        ArrayList<Habitacion> habitaciones = json.hoteles.get(j).getHabitaciones();
                        for(int k = 0; k < habitaciones.size(); k++){
                            i.putExtra("tipohabitacion"+(k+1), habitaciones.get(k).getTipo());
                            i.putExtra("personashabitacion"+(k+1), habitaciones.get(k).getPersonas());
                            i.putExtra("preciohabitacion"+(k+1), habitaciones.get(k).getPrecio());
                        }
                        j = json.hoteles.size();
                    }
                }
                startActivity(i);
            }
        });
    }

        @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, NuevoEquipaje.class);
        startActivity(intent);

    }

    class AdaptadorHoteles extends ArrayAdapter<String> {

        Context context;
        int[] imagenes;
        String[] titleArray;
        Map<String,String> desc;

        AdaptadorHoteles(Context c, String[] titulos, int[] imgs, Map<String,String> descr) {
            super(c, R.layout.renglon_hoteles, R.id.tituloEquipaje, titulos);
            this.context = c;
            this.imagenes = imgs;
            this.titleArray = titulos;
            this.desc = descr;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.renglon_hoteles, parent, false);
            ImageView logo = (ImageView) row.findViewById(R.id.logoEquipaje);
            TextView titulo = (TextView) row.findViewById(R.id.tituloEquipaje);
            TextView objs = (TextView) row.findViewById(R.id.objetosEquipaje);
            String objetos = "";
            Iterator it = desc.keySet().iterator();
            while(it.hasNext()){
                String key = (String) it.next();
                if(key.compareTo(titleArray[position]) == 0)
                    objetos = desc.get(key)+" ";
            }
            logo.setImageResource(imagenes[obtenerImagen(titleArray[position])-1]);
            titulo.setText(titleArray[position]);
            objs.setText(objetos);

            return row;
        }
    }

    public int obtenerImagen(String titulo){
        return estrellas.get(titulo);
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
