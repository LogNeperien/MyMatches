package com.edu.ck.mymatches;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Informations extends AppCompatActivity {

    //public static final String EQUIPE1 = "EQUIPE1";
    //public static final String EQUIPE2 = "EQUIPE2";
    private TextView e1, e2, liste1, liste2;
    DatabaseHelper db;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        e1 = findViewById(R.id.nomEquipe1);
        e2 = findViewById(R.id.nomEquipe2);
        liste1 = findViewById(R.id.listeEquipe1);
        liste2 = findViewById(R.id.listeEquipe2);

        Intent intent = getIntent();

        //Affichage des noms des équipes
        String equipe1 = intent.getStringExtra(Match.EQUIPE1);
        String equipe2 = intent.getStringExtra(Match.EQUIPE2);
        e1.setText(equipe1);
        e2.setText(equipe2);

        //Affichage de la liste des joueurs
        //Avec bdd (exemple avec id = 1)
        db = new DatabaseHelper(this);
        Cursor data = db.getEquipes(1);

        if(data.getCount() == 0)
        {
            Toast.makeText(Informations.this,"Error, No Data Found !!",Toast.LENGTH_LONG).show();
            return;
        }
        while(data.moveToNext())
        {
            liste1.setText(data.getString(0) + data.getString(2)
                    + data.getString(3) + data.getString(4));
            liste2.setText(data.getString(1) + data.getString(5)
                    + data.getString(6) + data.getString(7));
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
