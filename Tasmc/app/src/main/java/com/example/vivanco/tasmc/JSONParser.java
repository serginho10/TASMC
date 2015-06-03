package com.example.vivanco.tasmc;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONParser {
    private ManejadorBD bd;
    private Activity activity;
    private JSONObject jObject;
    private ProgressDialog progressDialog = null;
    private Runnable runReadAndParseJSON;
    JSONArray objetosArray;
    ArrayList<Hotel> hoteles;
    Thread thread;

    public JSONParser(Activity a, Context context){
        activity = a;
        bd = new ManejadorBD(context);
    }

    public void readAndParseJSON(String objeto, final String cadena, final Object[] objetos) throws JSONException {
        final String cad = objeto;
        runReadAndParseJSON = new Runnable() {
            @Override
            public void run() {
                try{
                    readJSON(cad,cadena,objetos);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        thread = new Thread(null, runReadAndParseJSON, "bgReadJSONObjetos");
        thread.start();
        progressDialog = ProgressDialog.show(activity, "Descargando información", "Por favor espere",true);
        while(thread.isAlive()){}
    }

    public void readJSON(String objeto,String cadena,Object[] objetos) throws JSONException{
        switch (objeto){
            case "E":
                String url = "http://tasmc.16mb.com/service.php?cadena=Equipaje";
                System.out.println(url);
                jObject = JSONManager.getJSONfromURL(url);
                if(jObject != null) {
                    objetosArray = jObject.getJSONArray("Equipaje");
                    parseJSON("Equipaje");
                    objetosArray = jObject.getJSONArray("Objeto");
                    parseJSON("Objeto");
                    objetosArray = jObject.getJSONArray("Equipaje_has_Objeto");
                    parseJSON("Equipaje_has_Objeto");
                }
                break;
            case "H":
                String ur = "http://traso.besaba.com/service.php?cadena=Hotel&ciudad="+
                        cadena+"&personas="+objetos[0].toString().charAt(0)+"&categoria="+objetos[1].toString().charAt(0);
                System.out.println(ur);
                jObject = JSONManager.getJSONfromURL(ur);
                if(jObject != null) {
                    objetosArray = jObject.getJSONArray("Hotel");
                    parseJSON("Hotel");
                }
                break;
        }
        activity.runOnUiThread(returnRes);
    }

    public void parseJSON(String cadena) throws JSONException{
        if(cadena.compareTo("Equipaje") == 0) {
            for (int i = 0; i < objetosArray.length(); i++) {
                bd.guardarEquipajeX(objetosArray.getJSONObject(i).getInt("idEquipaje"),objetosArray.getJSONObject(i).getString("nombre"));
            }
        }else if(cadena.compareTo("Objeto") == 0){
            for (int i = 0; i < objetosArray.length(); i++)
                bd.guardarObjeto(new Objeto(objetosArray.getJSONObject(i).getInt("idObjeto"),
                        objetosArray.getJSONObject(i).getString("nombre"),
                        objetosArray.getJSONObject(i).getString("categoria")));
        }else if(cadena.compareTo("Equipaje_has_Objeto") == 0){
            for (int i = 0; i < objetosArray.length(); i++)
                bd.guardarEquipajeHasObjeto(new EquipajeHasObjeto(objetosArray.getJSONObject(i).getInt("Equipaje_idEquipaje"),
                        objetosArray.getJSONObject(i).getInt("Objeto_idObjeto")));
        }else if(cadena.compareTo("Hotel") == 0){
            ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
            hoteles = new ArrayList<Hotel>();
            for (int i = 0; i < objetosArray.length(); i++)
                hoteles.add(new Hotel(objetosArray.getJSONObject(i).getLong("idHotel"),
                        objetosArray.getJSONObject(i).getString("nombre"),
                        objetosArray.getJSONObject(i).getInt("categoria"),
                        objetosArray.getJSONObject(i).getString("web"),
                        objetosArray.getJSONObject(i).getString("telefono"),
                        objetosArray.getJSONObject(i).getInt("habitaciones"),
                        objetosArray.getJSONObject(i).getString("ciudad"),
                        null));
            objetosArray = jObject.getJSONArray("Habitacion");
            for (int i = 0; i < objetosArray.length(); i++)
                habitaciones.add(new Habitacion(objetosArray.getJSONObject(i).getInt("idHabitacion"),
                        objetosArray.getJSONObject(i).getString("tipo"),
                        objetosArray.getJSONObject(i).getInt("personas"),
                        objetosArray.getJSONObject(i).getInt("precio"),
                        objetosArray.getJSONObject(i).getInt("idHotel")));
            for(int i = 0; i < hoteles.size(); i++){
                long id = hoteles.get(i).getId();
                ArrayList<Habitacion> habHotel = new ArrayList<Habitacion>();
                for(int j = 0; j < habitaciones.size(); j++){
                    if(id == habitaciones.get(j).getIdHotel()){
                        habHotel.add(habitaciones.get(j));
                    }
                }
                hoteles.get(i).setHabitaciones(habHotel);
            }
        }
    }

    private Runnable returnRes = new Runnable() {
        @Override
        public void run() {
            progressDialog.dismiss();
        }
    };
}

