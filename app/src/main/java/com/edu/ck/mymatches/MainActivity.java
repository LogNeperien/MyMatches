package com.edu.ck.mymatches;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String ID = "ID_MATCH";
    TextView match1, match2, match3, match4, match5;
    TextView date1, date2, date3, date4, date5;
    DatabaseHelper db;
    ArrayList<MatchC> PreviousMatchs;
    int id;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialisation des Views
        match1 = findViewById(R.id.match1);
        match2 = findViewById(R.id.match2);
        match3 = findViewById(R.id.match3);
        match4 = findViewById(R.id.match4);
        match5 = findViewById(R.id.match5);
        date1 = findViewById(R.id.date1);
        date2 = findViewById(R.id.date2);
        date3 = findViewById(R.id.date3);
        date4 = findViewById(R.id.date4);
        date5 = findViewById(R.id.date5);


        //Récupération des 5 derniers matchs BDD SQLite
        db = new DatabaseHelper(this);

        PreviousMatchs = (ArrayList<MatchC>) db.getPreviousMatchs();



        match1.setText(PreviousMatchs.get(0).getEquipe1() + " - " + PreviousMatchs.get(0).getEquipe2());
        match2.setText(PreviousMatchs.get(1).getEquipe1() + " - " + PreviousMatchs.get(1).getEquipe2());
        match3.setText(PreviousMatchs.get(2).getEquipe1() + " - " + PreviousMatchs.get(2).getEquipe2());
        match4.setText(PreviousMatchs.get(3).getEquipe1() + " - " + PreviousMatchs.get(3).getEquipe2());
        match5.setText(PreviousMatchs.get(4).getEquipe1() + " - " + PreviousMatchs.get(4).getEquipe2());


        date1.setText(Integer.toString(PreviousMatchs.get(0).getDate()));
        date2.setText(Integer.toString(PreviousMatchs.get(1).getDate()));
        date3.setText(Integer.toString(PreviousMatchs.get(2).getDate()));
        date4.setText(Integer.toString(PreviousMatchs.get(3).getDate()));
        date5.setText(Integer.toString(PreviousMatchs.get(4).getDate()));


    }

    public void launchSecondActivity(View view) {
        switch (view.getId()) {

            case R.id.button:
                //Envoi de l'id et nouvelle activité
                Intent i = new Intent(this, Match.class);
                id = PreviousMatchs.get(0).getId();
                i.putExtra(ID, id);
                startActivity(i);
                break;
            case R.id.button2:
                //Envoi de l'id et nouvelle activité
                i = new Intent(this, Match.class);
                id = PreviousMatchs.get(1).getId();
                i.putExtra(ID, id);
                startActivity(i);
                break;
            case R.id.button3:
                //Envoi de l'id et nouvelle activité
                i = new Intent(this, Match.class);
                id = PreviousMatchs.get(2).getId();
                i.putExtra(ID, id);
                startActivity(i);
                break;
            case R.id.button4:
                //Envoi de l'id et nouvelle activité
                i = new Intent(this, Match.class);
                id = PreviousMatchs.get(3).getId();
                i.putExtra(ID, id);
                startActivity(i);
                break;
            case R.id.button5:
                //Envoi de l'id et nouvelle activité
                i = new Intent(this, Match.class);
                id = PreviousMatchs.get(4).getId();
                i.putExtra(ID, id);
                startActivity(i);
                break;
            case R.id.buttonMatch:
                //Envoi sur page de tous les matchs
                //i = new Intent(this, AllMatchs.class);
                //startActivity(i);
                break;
            case R.id.buttonAjout:
                i = new Intent(this, NewMatchActivity.class);
                startActivity(i);
                break;
            default:
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
