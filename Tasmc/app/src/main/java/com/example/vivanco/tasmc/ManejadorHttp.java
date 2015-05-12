package com.example.vivanco.tasmc;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ISC_SERGIO on 11/05/15.
 */
public class ManejadorHttp extends AsyncTask{
    @Override
    protected Object doInBackground(Object[] usr) {
        try {
            Usuario usuario = (Usuario) usr[0];
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://www.tasmc.16mb.com/service.php");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("email",usuario.getEmail()));
            params.add(new BasicNameValuePair("categoria", usuario.getCategoria()));
            params.add(new BasicNameValuePair("clase", usuario.getClase()));
            params.add(new BasicNameValuePair("tipo", usuario.getTipo()));
            params.add(new BasicNameValuePair("Equipaje_idEquipaje", usuario.getEquipaje_idEquipaje() + ""));
            httppost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse resp = httpclient.execute(httppost);
            HttpEntity ent = resp.getEntity();/*y obtenemos una respuesta*/
            System.out.println(EntityUtils.toString(ent)+"***************************");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
