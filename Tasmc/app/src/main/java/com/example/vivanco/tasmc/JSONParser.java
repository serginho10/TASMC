package com.example.vivanco.tasmc;

import android.app.Activity;
import android.app.ProgressDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ISC_SERGIO on 25/03/15.
 */
public class JSONParser {
    private Activity activity;
    private JSONObject jObject;
    private ProgressDialog progressDialog = null;
    private Runnable runReadAndParseJSON;
    JSONArray objetosArray;
    String[] res;

    public JSONParser(Activity a){
        activity = a;
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
        }
        activity.runOnUiThread(returnRes);
    }

    public void parseJSON(String cadena) throws JSONException{
        res = new String[objetosArray.length()];
        for(int i = 0; i < objetosArray.length(); i++){
            res[i] = (objetosArray.getJSONObject(i).getString("nombre"));
            System.out.println(res[i]);
        }
    }

    private Runnable returnRes = new Runnable() {
        @Override
        public void run() {
            progressDialog.dismiss();
        }
    };
}

