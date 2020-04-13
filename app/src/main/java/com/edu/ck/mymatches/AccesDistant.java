package com.edu.ck.mymatches;

import android.util.Log;

public class AccesDistant implements AsyncResponse {

    private static final String SERVEUR_ADDR = "http://192.168.0.33/MyMatches/serveurmatch.php";

    public AccesDistant(){ super(); }

    //S'execute au retour du serveur distant
    @Override
    public void processFinish(String output) {
        Log.d("serveur", "**** " + output);


    }
}
