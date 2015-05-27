package com.example.vivanco.tasmc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;


public class Configuracion extends ActionBarActivity {
    Spinner categorias;
    Spinner clases;
    TextView textEmail;
    TextView textClase;
    TextView textCatego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar);

        JSONParser json = new JSONParser(this,getApplicationContext());
        try {
            json.readAndParseJSON("Equipaje");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        LayoutInflater inflater = getLayoutInflater();
        View appeareance = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.mensaje));
        toast.setView(appeareance);

        final ManejadorBD bd = new ManejadorBD(getApplicationContext());
        if (bd.existeUsuario()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }else{
            toast.show();
            toast.show();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setLogo(R.drawable.avionblan); //logo
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//quitamos texto de toolbar

        //Poner texto al textview determinado
        textEmail = (TextView) findViewById(R.id.textEmail);
        textClase = (TextView) findViewById(R.id.textClase);
        textCatego = (TextView) findViewById(R.id.textCatego);

        final EditText ETemail = (EditText) findViewById(R.id.email);
        clases = (Spinner) findViewById(R.id.clase);
        categorias = (Spinner) findViewById(R.id.categoria);
        Button btnListo = (Button) findViewById(R.id.listo);

        btnListo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ETemail.getText().length() != 0) {
                    Usuario usuario = new Usuario(ETemail.getText().toString(), categorias.getSelectedItem().toString(),
                            clases.getSelectedItem().toString(), "usuario", 1, 1);
                    System.out.println(categorias.getSelectedItem().toString() + " ************* " + clases.getSelectedItem().toString());
                    bd.guardarUsuario(usuario);
                    finish();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }else ETemail.setError("Debes ingresar un email.");
            }
        });
        ArrayAdapter dataAdapter = ArrayAdapter.createFromResource(this, R.array.clases, android.R.layout.simple_spinner_item);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clases.setAdapter(dataAdapter);
        //spinner hotel estrellas
        ArrayAdapter dataAdapter1 = ArrayAdapter.createFromResource(this, R.array.estrellas, android.R.layout.simple_spinner_item);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorias.setAdapter(dataAdapter1);

        Button btnContinuar = (Button) findViewById(R.id.btnContinuar);
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario("", "",
                        "", "usuario", 1, 1);
                bd.guardarUsuario(usuario);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
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
