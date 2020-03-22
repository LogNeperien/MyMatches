package com.edu.ck.mymatches;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDataBase;
    TextView nameMatch1 = (TextView) findViewById(R.id.nameMatch1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        myDataBase = new DatabaseHelper(this);

        ContentValues values = new ContentValues();
        values.put("nom", "Stade Français");
        values.put("entraineur", "Sempéré");
        values.put("joueur1", "ANGAELANGI");
        values.put("joueur2", "Alo-Emile");
        values.put("joueur3", "coucou");
        values.put("joueur4", "salut");
        values.put("joueur5", "hello");
        values.put("joueur6", "oupsi");
        values.put("joueur7", "dak");
        values.put("joueur8", "dakokdak");
        values.put("joueur9", "joeuur");
        values.put("joueur10", "joueuseu");
        values.put("joueur11", "resallut");
        values.put("joueur12", "bonjour");
        values.put("joueur13", "bonsoir");
        values.put("joueur14", "clong");
        values.put("joueur15", "coucou1coucou2");

        myDataBase.insertData("equipe", values);

        Cursor result = myDataBase.getDataEquipe("equipe");
        result.moveToFirst();
        while(!result.isAfterLast())
        {
            nameMatch1.setText(result.getString(0));
            result.moveToNext();
        }
        result.close();
    }

    public void launchSecondActivity(View view) {
        Intent intent = new Intent(this, Match.class);
        startActivity(intent);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
