package com.example.vivanco.tasmc;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;


public class Configuracion extends ActionBarActivity {
    Spinner categorias;
    String[] opcatego = new String[]{"1 estrella", "2 estrellas", "3 estrellas", "4 estrellas", "5 estrellas"};
    Spinner clases;
    String[] opclase = new String[]{"Económico", "Económico Premium", "Business", "Primera"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar);

        final ManejadorBD bd = new ManejadorBD(getApplicationContext());
        if(bd.existeUsuario())
            startActivity(new Intent(this, MainActivity.class));

        final EditText ETemail = (EditText) findViewById(R.id.email);
        clases = (Spinner) findViewById(R.id.clase);
        categorias = (Spinner) findViewById(R.id.categoria);
        Button btnListo = (Button) findViewById(R.id.listo);

        btnListo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario(1,ETemail.getText().toString(),categorias.getSelectedItem().toString(),
                        clases.getSelectedItem().toString(),"usuario","vuelo",1,1);
                bd.guardarUsuario(usuario);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opclase);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clases.setAdapter(dataAdapter);
        //spinner hotel estrellas
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcatego);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorias.setAdapter(dataAdapter1);

        Button btnContinuar = (Button) findViewById(R.id.btnContinuar);
        btnContinuar.setText("Continuar sin configurar");
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario(1,"","",
                        "","usuario","vuelo",1,1);
                bd.guardarUsuario(usuario);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_configuracion, menu);
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
}
