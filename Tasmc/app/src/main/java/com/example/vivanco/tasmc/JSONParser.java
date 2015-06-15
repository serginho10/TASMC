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
    ArrayList<Vuelo> vuelos;

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
            case "V":
                String u = "http://traso.besaba.com/service.php?cadena=Vuelo&ciudadOrigen="+objetos[0].toString()+
                        "&ciudadDestino="+objetos[1].toString()+"&fecha="+objetos[2].toString()+"&clase="+objetos[3].toString();
                System.out.println(u);
                jObject = JSONManager.getJSONfromURL(u);
                if(jObject != null){
                    objetosArray = jObject.getJSONArray("Vuelo");
                    parseJSON("Vuelo");
                }
                break;
            case "I":
                String uerrele = "http://traso.besaba.com/service.php?cadena=info";
                System.out.println(uerrele);
                jObject = JSONManager.getJSONfromURL(uerrele);
                if(jObject != null){
                    objetosArray = jObject.getJSONArray("Vuelo");
                    parseJSON("info");
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
        }else if(cadena.compareTo("Vuelo") == 0){
            vuelos = new ArrayList<Vuelo>();
            for(int i = 0; i < objetosArray.length(); i++){
                vuelos.add(new Vuelo(objetosArray.getJSONObject(i).getLong("idVuelo"),
                        objetosArray.getJSONObject(i).getString("numero"),
                        objetosArray.getJSONObject(i).getString("salida"),
                        objetosArray.getJSONObject(i).getString("llegada"),null,null,null,
                        objetosArray.getJSONObject(i).getString("aerolinea"),
                        objetosArray.getJSONObject(i).getString("origen"),
                        objetosArray.getJSONObject(i).getString("destino")));
            }
        }else if(cadena.compareTo("info") == 0){
            vuelos = new ArrayList<Vuelo>();
            for(int i = 0; i < objetosArray.length(); i++){
                vuelos.add(new Vuelo(objetosArray.getJSONObject(i).getLong("idVuelo"),
                        objetosArray.getJSONObject(i).getString("numero"),
                        objetosArray.getJSONObject(i).getString("salida"),
                        objetosArray.getJSONObject(i).getString("llegada"),
                        objetosArray.getJSONObject(i).getString("estado"),
                        objetosArray.getJSONObject(i).getString("sala"),
                        objetosArray.getJSONObject(i).getString("terminal"),
                        objetosArray.getJSONObject(i).getString("aerolinea"),
                        objetosArray.getJSONObject(i).getString("origen"),
                        objetosArray.getJSONObject(i).getString("destino")));
            }
            System.out.println(vuelos.size());
        }
    }

    private Runnable returnRes = new Runnable() {
        @Override
        public void run() {
            progressDialog.dismiss();
        }
    };
}

