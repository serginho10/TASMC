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

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;

import java.util.Iterator;
import java.util.Map;

public class ListEquipaje extends ActionBarActivity implements View.OnClickListener{

    ListView list;
    String[] titulos;
    Map<String,String> descripcion;
    int[] image = {R.drawable.business};
    private static final String TAG_NUEVO="nuevo equipaje";
    ManejadorBD bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_equipaje);
        bd = new ManejadorBD(getApplicationContext());
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_eq);
        setSupportActionBar(toolbar);

        //Habilita el boton para ir a la actividad principal en el Toolbar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        titulos = bd.obtenerNombresEquipajes();
        descripcion = bd.obtenerObjetosDEquipaje();

        list = (ListView) findViewById(R.id.listEquipaje);
        AdaptadorEquipaje adaptadorEquipaje = new AdaptadorEquipaje(this, titulos, image, descripcion);
        list.setAdapter(adaptadorEquipaje);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(),EquipajeSeleccionado.class);
                i.putExtra("equipaje",list.getItemAtPosition(position).toString());
                startActivity(i);
            }
        });

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



        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .attachTo(actionButton)
                .build();
        actionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
        Intent intent = new Intent(this, NuevoEquipaje.class);
        startActivity(intent);

    }

    class AdaptadorEquipaje extends ArrayAdapter<String> {

        Context context;
        int[] imagenes;
        String[] titleArray;
        Map<String,String> desc;

        AdaptadorEquipaje(Context c, String[] titulos, int[] imgs, Map<String,String> descr) {
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
            logo.setImageResource(imagenes[0]);
            titulo.setText(titleArray[position]);
            objs.setText(objetos);

            return row;
        }
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
