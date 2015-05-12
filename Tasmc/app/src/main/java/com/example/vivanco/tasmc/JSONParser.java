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
                }catch(Exception e){}
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
            parseJSON(cadena);
            objetosArray = jObject.getJSONArray("Equipaje_has_Objeto");
            parseJSON(cadena);
        }
        activity.runOnUiThread(returnRes);
    }

    public void parseJSON(String cadena) throws JSONException{
        if(cadena.compareTo("Equipaje") == 0) {
            res = new String[objetosArray.length()];
            for (int i = 0; i < objetosArray.length(); i++) {
                res[i] = (objetosArray.getJSONObject(i).getString("nombre"));
            }
        }else if(cadena.compareTo("Objeto") == 0){
            objetos = new String[objetosArray.length()];
            for (int i = 0; i < objetosArray.length(); i++) {
                res[i] = (objetosArray.getJSONObject(i).getString("nombre"));
            }
        }else if(cadena.compareTo("Equipaje_has_Objeto") == 0){
            relacion = new String[objetosArray.length()];
            for (int i = 0; i < objetosArray.length(); i++) {
                res[i] = (objetosArray.getJSONObject(i).getString("nombre"));
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

