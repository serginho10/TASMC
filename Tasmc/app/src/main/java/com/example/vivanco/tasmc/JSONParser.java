package com.example.vivanco.tasmc;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser {
    private ManejadorBD bd;
    private Activity activity;
    private JSONObject jObject;
    private ProgressDialog progressDialog = null;
    private Runnable runReadAndParseJSON;
    JSONArray objetosArray;
    String[] res;
    String[] objetos;
    String[] relacion;
    int[] image;
    Bitmap[] logo;

    public JSONParser(Activity a, Context context){
        activity = a;
        bd = new ManejadorBD(context);
    }

    public void readAndParseJSON(String cadena) throws JSONException {
        final String cad = cadena;
        runReadAndParseJSON = new Runnable() {
            @Override
            public void run() {
                try{
                    readJSON(cad);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(null, runReadAndParseJSON, "bgReadJSONObjetos");
        thread.start();
        progressDialog = ProgressDialog.show(activity, "Descargando información", "Por favor espere",true);
    }

    public void readJSON(String cadena) throws JSONException{
        jObject = JSONManager.getJSONfromURL("http://tasmc.16mb.com/service.php?cadena=Equipaje");
        if(jObject != null) {
            objetosArray = jObject.getJSONArray(cadena);
            parseJSON(cadena);
            objetosArray = jObject.getJSONArray("Objeto");
            parseJSON("Objeto");
            objetosArray = jObject.getJSONArray("Equipaje_has_Objeto");
            parseJSON("Equipaje_has_Objeto");
        }
        activity.runOnUiThread(returnRes);
    }

    public void parseJSON(String cadena) throws JSONException{
        if(cadena.compareTo("Equipaje") == 0) {
            bd.borrarTodoEquipaje();
            for (int i = 0; i < objetosArray.length(); i++) {
                bd.guardarEquipajeX(objetosArray.getJSONObject(i).getInt("idEquipaje"),objetosArray.getJSONObject(i).getString("nombre"));
            }
        }else if(cadena.compareTo("Objeto") == 0){
            bd.borrarTodoObjeto();
            for (int i = 0; i < objetosArray.length(); i++)
                bd.guardarObjeto(new Objeto(objetosArray.getJSONObject(i).getInt("idObjeto"),
                        objetosArray.getJSONObject(i).getString("nombre"),
                        objetosArray.getJSONObject(i).getString("categoria")));
        }else if(cadena.compareTo("Equipaje_has_Objeto") == 0){
            bd.borrarTodoEquipajeHasObjeto();
            for (int i = 0; i < objetosArray.length(); i++)
                bd.guardarEquipajeHasObjeto(new EquipajeHasObjeto(objetosArray.getJSONObject(i).getInt("Equipaje_idEquipaje"),
                        objetosArray.getJSONObject(i).getInt("Objeto_idObjeto")));
        }
    }

    private Runnable returnRes = new Runnable() {
        @Override
        public void run() {
            progressDialog.dismiss();
        }
    };
}

