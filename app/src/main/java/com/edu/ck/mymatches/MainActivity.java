package com.edu.ck.mymatches;

import android.content.ContentValues;
import android.content.Context;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    private DatabaseHelper myDataBase;
    private TextView nameMatch1;


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

        nameMatch1 = (TextView) findViewById(R.id.nameMatch1);
        LinearLayout layoutMatchs = (LinearLayout)findViewById(R.id.matchLayout);
        layoutMatchs.removeAllViews();
        //myDataBase = new DatabaseHelper(this);
        myDataBase = new DatabaseHelper(getApplicationContext());

        boolean insertion = insertData("equipe");


        //affichage d'un toast pour savoir si l'ajout a été réussi ou pas
        Context context = getApplicationContext();
        CharSequence text;
        int duration = Toast.LENGTH_SHORT;
        if(insertion)
        {
            text = "insertion réussie";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }else
        {
            text = "insertion ratée";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        Cursor result = myDataBase.getDataEquipe("Stade Français");
        result.moveToFirst();
        String matchEntraineur = "";
        String NomEquipe = "";
        while(!result.isAfterLast())
        {
            NomEquipe = result.getString(result.getColumnIndexOrThrow("nom"));
            matchEntraineur = result.getString(result.getColumnIndexOrThrow("entraineur"));
            result.moveToNext();
        }
        TextView newNomEquipe = new TextView(this);
        newNomEquipe.setText(NomEquipe);
        layoutMatchs.addView(newNomEquipe);
        TextView newEntraineur = new TextView(this);
        newEntraineur.setText(matchEntraineur);
        layoutMatchs.addView(newEntraineur);

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

    public boolean insertData(String name_table)
    {
        SQLiteDatabase db = myDataBase.getWritableDatabase();

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

        long resultInsert;
        resultInsert = db.insert(name_table,null, values);
        if(resultInsert == -1)
            return false;
        else
            return true;
    }
}
