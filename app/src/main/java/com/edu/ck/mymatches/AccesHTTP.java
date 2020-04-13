package com.edu.ck.mymatches;

import android.os.AsyncTask;
import android.util.Log;

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
import java.net.HttpCookie;
import java.util.ArrayList;

public class AccesHTTP extends AsyncTask<String, Integer, Long> {

    private ArrayList<NameValuePair> parametres;
    private String retour = null;
    private AsyncResponse delegate = null;

    public AccesHTTP()
    {
        parametres = new ArrayList<NameValuePair>();
    }

    //Ajout d'un parametre POST
    public void addParams(String nom, String value)
    {
        parametres.add(new BasicNameValuePair(nom, value));


    }

    @Override
    protected Long doInBackground(String... strings) {
        HttpClient cnx = new DefaultHttpClient();
        HttpPost paramCnx = new HttpPost(strings[0]);

        try {
            //Encodage des parametres
            paramCnx.setEntity(new UrlEncodedFormEntity(parametres));

            //Connexion et envoi des parametres, attente de réponse
            HttpResponse reponse = cnx.execute(paramCnx);

            //Transformation de la réponse
            retour = EntityUtils.toString(reponse.getEntity());

        } catch (UnsupportedEncodingException e) {
            Log.d("erreur encodage", "****" + e.toString());


        } catch (ClientProtocolException e) {
            Log.d("erreur protocole", "****" + e.toString());
        } catch (IOException e) {
            Log.d("erreur Input/Output", "****" + e.toString());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Long result)
    {
        delegate.processFinish(retour);
    }
}
