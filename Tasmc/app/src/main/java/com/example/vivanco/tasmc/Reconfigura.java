package com.example.vivanco.tasmc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by ISC_SERGIO on 29/04/15.
 */
public class Reconfigura extends ActionBarActivity {
    Spinner categorias;
    Spinner clases;
    TextView textEmail;
    TextView textClase;
    TextView textCatego;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        //Poner texto al textview determinado
        textEmail = (TextView) findViewById(R.id.textEmail);
        textClase = (TextView) findViewById(R.id.textClase);
        textCatego = (TextView) findViewById(R.id.textCatego);


        final EditText ETemail = (EditText) findViewById(R.id.email);
        clases = (Spinner) findViewById(R.id.clase);
        categorias = (Spinner) findViewById(R.id.categoria);
        Button btnListo = (Button) findViewById(R.id.listo);

        Usuario usuario;

        ArrayAdapter dataAdapter = ArrayAdapter.createFromResource(this, R.array.clases, android.R.layout.simple_spinner_item);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clases.setAdapter(dataAdapter);
        //spinner hotel estrellas
        ArrayAdapter dataAdapter1 = ArrayAdapter.createFromResource(this, R.array.estrellas, android.R.layout.simple_spinner_item);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorias.setAdapter(dataAdapter1);

        final ManejadorBD bd = new ManejadorBD(getApplicationContext());
        if (bd.existeUsuario()) {
            usuario = bd.getUsuario();
            ETemail.setText(usuario.getEmail());
            String categoria = usuario.getCategoria();
            if (categoria.compareTo("1 estrella") == 0)
                categorias.setSelection(0);
            else if (categoria.compareTo("2 estrellas") == 0)
                categorias.setSelection(1);
            else if (categoria.compareTo("3 estrellas") == 0)
                categorias.setSelection(2);
            else if (categoria.compareTo("4 estrellas") == 0)
                categorias.setSelection(3);
            else if (categoria.compareTo("5 estrellas") == 0)
                categorias.setSelection(4);
            String clase = usuario.getClase();
            if (clase.compareTo("Económico") == 0)
                clases.setSelection(0);
            else if (clase.compareTo("Económico Premium") == 0)
                clases.setSelection(1);
            else if (clase.compareTo("Business") == 0)
                clases.setSelection(2);
            else if (clase.compareTo("Primera") == 0)
                clases.setSelection(3);
            System.out.println(categoria+" ************** "+clase);
        }


        btnListo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(ETemail.getText().toString() + "*******************");
                System.out.println(categorias.getSelectedItem().toString() + "*******************");
                System.out.println(clases.getSelectedItem().toString() + "*******************");
                Usuario user = new Usuario(ETemail.getText().toString(), categorias.getSelectedItem().toString(),
                        clases.getSelectedItem().toString(), "usuario", 1, 1);
                bd.guardarUsuario(user);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        //Habilita el boton para ir a la actividad principal en el Toolbar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
