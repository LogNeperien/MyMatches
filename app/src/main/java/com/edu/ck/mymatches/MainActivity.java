package com.edu.ck.mymatches;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String ID = "ID_MATCH";
    TextView match1, match2, match3, match4, match5;
    DatabaseHelper db;

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

        //BDD
        db = new DatabaseHelper(this);

        Cursor data = db.getPreviousMatchs();

        if(data.getCount() == 0)
        {
            Toast.makeText(MainActivity.this,"Error, No Data Found !!",Toast.LENGTH_LONG).show();
            return;
        }
        else
        {
            //L'appli ne se lance pas si récupération des données ici
            //match1.setText(data.getString(1));
        }

    }

    public void launchSecondActivity(View view) {
        switch (view.getId()) {

            case R.id.buttonMatch:
                //if(id )
                Intent i = new Intent(this, Match.class);
                startActivity(i);
                break;

            case R.id.buttonAjout:
                i = new Intent(this, NewMatchActivity.class);
                startActivity(i);
                break;
            default:
                //Envoyer l'id du match dans un extra en fonction du bouton cliqué
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
