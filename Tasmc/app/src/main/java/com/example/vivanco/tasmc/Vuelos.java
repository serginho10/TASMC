package com.example.vivanco.tasmc;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Vuelos extends ActionBarActivity implements View.OnClickListener{

    Spinner clase;
    EditText origen;
    EditText destino;
    EditText fecha;
    DatePickerDialog dialogoFecha;
    SimpleDateFormat formatoFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_ida);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar_vuelo);
        setSupportActionBar(toolbar);

        origen = (EditText) findViewById(R.id.etCiudadOrigen);
        destino = (EditText) findViewById(R.id.etCiudadDestino);
        String[] array;

        //Habilita el boton para ir a la actividad principal en el Toolbar

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ManejadorBD bd = new ManejadorBD(getApplicationContext());
        String claseUsuario = bd.getClaseUsuario();

        array = getResources().getStringArray(R.array.clases);
        clase = (Spinner) findViewById(R.id.sClase);
        ArrayAdapter adaptador = new ArrayAdapter(this, R.layout.spinner_text_layout, array);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clase.setAdapter(adaptador);
        if(claseUsuario.compareTo("Económino") == 0)
            clase.setSelection(0);
        else if(claseUsuario.compareTo("Económino Premium") == 0)
            clase.setSelection(1);
        else if(claseUsuario.compareTo("Negocios") == 0)
            clase.setSelection(2);
        else if(claseUsuario.compareTo("Primera") == 0)
            clase.setSelection(3);

        formatoFecha = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        fecha = (EditText) findViewById(R.id.etFecha);
        fecha.setInputType(InputType.TYPE_NULL);
        fecha.requestFocus();
        fecha.setOnClickListener(this);

        Calendar calendarioNuevo = Calendar.getInstance();
        dialogoFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar fechaNueva = Calendar.getInstance();
                fechaNueva.set(year, monthOfYear, dayOfMonth);
                fecha.setText(formatoFecha.format(fechaNueva.getTime()));
            }
        }, calendarioNuevo.get(Calendar.YEAR), calendarioNuevo.get(Calendar.MONTH), calendarioNuevo.get(Calendar.DAY_OF_MONTH));
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

    public void buscar(View view){
        if(origen.getText().length() == 0){
            origen.setError("Debes ingresar tu ciudad de origen.");
        }else if(destino.getText().length() == 0){
            destino.setError("Debes ingresar tu ciudad de destino.");
        }else if(fecha.getText().length() == 0){
            fecha.setError("Debes ingresar la fecha de tu vuelo.");
        }else{
            JSONParser json = new JSONParser(this,getApplicationContext());
            try{
                json.readAndParseJSON("V","",new Object[]{origen.getText().toString().toUpperCase(),
                        destino.getText().toString().toUpperCase(),
                        fecha.getText().toString(), clase.getSelectedItem().toString().toUpperCase()});
            }catch (JSONException e){
                e.printStackTrace();
            }
            while (json.thread.isAlive()){}
            for (int i = 0; i < json.vuelos.size(); i++){
                System.out.println(json.vuelos.get(i).toString());
            }
        }
    }

    @Override
    public void onClick(View v) {
        dialogoFecha.show();
    }
}
