package com.edu.ck.mymatches;

import android.util.Log;

import org.json.JSONArray;

public class AccesDistant implements AsyncResponse {

    private static final String SERVEUR_ADDR = "https://192.168.0.33:3306/MyMatches/serveurmatch.php";

    public AccesDistant(){ super(); }

    //S'execute au retour du serveur distant
    @Override
    public void processFinish(String output) {
        Log.d("serveur", "********" + output);

        //Découpage du message reçu
        String[] message = output.split("%");

        //Vérification reception des 2 parties
        if(message.length > 1)
        {
            if(message[0].equals("ajout"))
            {
                Log.d("ajout", "********" + message[1]);
            }
            else if(message[0].equals("ERREUR php !"))
            {
                Log.d("erreur php", "********" + message[1]);
            }
        }

    }

    public void envoi(String operation, JSONArray lesdonneesJSON)
    {
        Log.d("operation", "**************" + operation);
        Log.d("lesD0onnes", "**********" + lesdonneesJSON);
        AccesHTTP accesDonnees = new AccesHTTP();

        //Lien de délégation
        accesDonnees.delegate = this;

        //Ajout de parametres
        accesDonnees.addParams("operation", operation);
        accesDonnees.addParams("lesdonnees", lesdonneesJSON.toString());

        //Appel au serveur
        accesDonnees.execute(SERVEUR_ADDR);
    }
}
