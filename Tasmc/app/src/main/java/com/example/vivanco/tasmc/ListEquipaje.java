package com.example.vivanco.tasmc;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

public class ListEquipaje extends ActionBarActivity implements View.OnClickListener{

    ListView list;
    String[] titulos;
    String[] descripcion;
    int[] image = {R.drawable.romantico, R.drawable.familia, R.drawable.business, R.drawable.general};
    private static final String TAG_NUEVO="nuevo equipaje";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_equipaje);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_equi);
        setSupportActionBar(toolbar);

        //Habilita el boton para ir a la actividad principal en el Toolbar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Buscar forma de meter los nuevos
        titulos = new String[]{"Romantico", "Familia", "Business", "General"};
        descripcion = new String[]{"abridor de botellas, velas y cerillos, perfume",
                "protector solar, medicamento, comida, juguetes",
                "pasaporte, traje, computadora",
                "cargador de celular, efectivo, camara"};

        list = (ListView) findViewById(R.id.listEquipaje);
        AdaptadorEquipaje adaptadorEquipaje = new AdaptadorEquipaje(this, titulos, image, descripcion);
        list.setAdapter(adaptadorEquipaje);

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
        ImageView iconEliminar = new ImageView(this);
        iconEliminar.setImageResource(R.drawable.ic_action_important);
        ImageView iconEditar = new ImageView(this);
        iconEditar.setImageResource(R.drawable.ic_action_new);
        //Subopciones
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        //Background de presionado para los sub botones
        itemBuilder.setBackgroundDrawable(getResources().getDrawable(R.drawable.selector_sub_button_gray));
        SubActionButton btnNuevo = itemBuilder.setContentView(iconNuevo).build();
        SubActionButton btnEliminar = itemBuilder.setContentView(iconEliminar).build();
        SubActionButton btnEditar = itemBuilder.setContentView(iconEditar).build();

        //Action al presionar
        btnNuevo.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
        btnEditar.setOnClickListener(this);

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(btnNuevo)
                .addSubActionView(btnEliminar)
                .addSubActionView(btnEditar)
                .attachTo(actionButton)
                .build();
    }

    @Override
    public void onClick(View v) {

    }

    class AdaptadorEquipaje extends ArrayAdapter<String> {

        Context context;
        int[] imagenes;
        String[] titleArray;
        String[] desc;

        AdaptadorEquipaje(Context c, String[] titulos, int[] imgs, String[] descr) {
            super(c, R.layout.renglon_equipaje, R.id.tituloEquipaje, titulos);
            this.context = c;
            this.imagenes = imgs;
            this.titleArray = titulos;
            this.desc = descr;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.renglon_equipaje, parent, false);
            ImageView logo = (ImageView) row.findViewById(R.id.logoEquipaje);
            TextView titulo = (TextView) row.findViewById(R.id.tituloEquipaje);
            TextView objs = (TextView) row.findViewById(R.id.objetosEquipaje);

            logo.setImageResource(imagenes[position]);
            titulo.setText(titleArray[position]);
            objs.setText(desc[position]);

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
