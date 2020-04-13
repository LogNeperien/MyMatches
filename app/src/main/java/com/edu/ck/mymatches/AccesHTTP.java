package com.edu.ck.mymatches;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpConnection;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class AccesHTTP extends AsyncTask<String, Integer, Long> {

    private String retour = "";
    private String parametres = "";
    public AsyncResponse delegate = null;

    public AccesHTTP()
    {
        super();
    }

    //Ajout d'un parametre POST
    public void addParams(String nom, String value)
    {
        try{
            if(parametres.equals(""))
            {
                //Premier parametre
                parametres = URLEncoder.encode(nom, "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8");
            }
            else
            {
                //Parametres suivants séparés par &
                parametres += "&" + URLEncoder.encode(nom, "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Long doInBackground(String... urls) {
        PrintWriter writer = null;
        BufferedReader reader = null;
        HttpURLConnection connexion = null;

        try{

            URL url = new URL(urls[0]);
            Log.d("DoInBack", "********** début" + url);

            //Ouverture de la connexion
            connexion = (HttpURLConnection) url.openConnection();
            connexion.setDoOutput(true);

            //Choix de la méthode POST pour l'envoie des paramètres
            connexion.setRequestMethod("POST");

            connexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connexion.setFixedLengthStreamingMode(parametres.getBytes().length);

            //Création de la requête d'envoi sur la connexion
            writer = new PrintWriter(connexion.getOutputStream());
            //writer.print(parametres);
            writer.write(parametres);

            writer.flush();

            //Récupération du retour du serveur
            reader = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
            retour = reader.readLine();

            Log.d("DoInBackFin", "**********" + retour);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try{
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        connexion.disconnect();
        return null;
    }

    @Override
    protected void onPostExecute(Long result)
    {
        delegate.processFinish(this.retour.toString());
    }
}
