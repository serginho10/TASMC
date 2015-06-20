package com.example.vivanco.tasmc;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Browser;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Attr;

import java.util.Random;

/**
 * Created by ISC_SERGIO on 14/06/15.
 */
public class HotelSeleccionado extends ActionBarActivity implements View.OnClickListener{

    String url;
    String numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_hotel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_hotel);
        setSupportActionBar(toolbar);

        //Habilita el boton para ir a la actividad principal en el Toolbar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*for (int j = 0; j < json.hoteles.size(); j++) {
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
        }*/

        Bundle bundle = getIntent().getExtras();
        setTitle(bundle.getString("hotel"));

        TextView tvCategoria = (TextView) findViewById(R.id.tvCategoria);
        TextView tvWeb = (TextView) findViewById(R.id.tvWeb);
        TextView tvTelefono = (TextView) findViewById(R.id.tvTelefono);
        TextView tvCiudad = (TextView) findViewById(R.id.tvCiudad);
        TextView tvHabitaciones = (TextView) findViewById(R.id.textView19);

        tvCategoria.setText(bundle.getInt("categoria")+" estrellas");
        url = bundle.getString("web");
        numero = bundle.getString("telefono");
        tvWeb.setOnClickListener(this);
        tvTelefono.setOnClickListener(this);
        tvCiudad.setText(bundle.getString("ciudad"));

        RelativeLayout layout = (RelativeLayout)findViewById(R.id.infoHotelLayout);

        int noHabitaciones = bundle.getInt("habitaciones");
        if(noHabitaciones == 0){
            tvHabitaciones.setVisibility(View.INVISIBLE);
        }
        System.out.println(noHabitaciones);

        int prevTextViewId = 0;
        for(int i = 0; i < noHabitaciones; i++)
        {
            final TextView textView = new TextView(this);
            textView.setText(bundle.getString("tipohabitacion"+(i+1)));
            textView.setTextAppearance(getApplicationContext(), R.style.TextAppearance_AppCompat_Large);
            textView.setTextColor(0xff000000);
            textView.setTypeface(null, Typeface.BOLD);
            int curTextViewId = prevTextViewId + 1;
            textView.setId(curTextViewId);
            final RelativeLayout.LayoutParams params =
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);
            if(i==0)
                params.addRule(RelativeLayout.BELOW, R.id.textView19);
            else
                params.addRule(RelativeLayout.BELOW, prevTextViewId);
            params.setMargins(0,15,0,0);
            textView.setLayoutParams(params);

            prevTextViewId = curTextViewId;
            layout.addView(textView, params);

            final TextView textView2 = new TextView(this);
            textView2.setText(bundle.getInt("personashabitacion" + (i + 1)) + " personas");
            textView2.setTextAppearance(getApplicationContext(), R.style.TextAppearance_AppCompat_Large);
            textView2.setTextColor(0xff000000);
            textView2.setTypeface(null, Typeface.NORMAL);
            curTextViewId = prevTextViewId + 1;
            textView2.setId(curTextViewId);
            final RelativeLayout.LayoutParams params2 =
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);
            params2.addRule(RelativeLayout.BELOW, prevTextViewId);
            params2.setMargins(0, 5, 0, 0);
            textView2.setLayoutParams(params2);

            prevTextViewId = curTextViewId;
            layout.addView(textView2, params2);

            final TextView textView3 = new TextView(this);
            if(bundle.getInt("preciohabitacion" + (i + 1)) == 0)
                textView3.setText("No hay precio publicado.");
            else
                textView3.setText("$ " + bundle.getInt("preciohabitacion" + (i + 1)) + ".00 m.n.");
            textView3.setTextAppearance(getApplicationContext(), R.style.TextAppearance_AppCompat_Large);
            textView3.setTextColor(0xff000000);
            textView3.setTypeface(null, Typeface.NORMAL);
            curTextViewId = prevTextViewId + 1;
            textView3.setId(curTextViewId);
            final RelativeLayout.LayoutParams params3 =
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);
            params3.addRule(RelativeLayout.BELOW, prevTextViewId);
            params3.setMargins(0,5,0,0);
            textView3.setLayoutParams(params3);

            prevTextViewId = curTextViewId;
            layout.addView(textView3, params3);
        }

    }

    public void onClick(View widget) {
        switch (widget.getId()){
            case R.id.tvWeb:
                Uri uri;
                if(url.contains("http://"))
                    uri = Uri.parse(url);
                else
                    uri = Uri.parse("http://"+url);
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
                break;
            case R.id.tvTelefono:
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:" + numero));
                startActivity(i);
                break;
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
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }

}
