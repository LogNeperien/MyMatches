package com.edu.ck.mymatches;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Match extends AppCompatActivity {

    public static final String EQUIPE1 = "EQUIPE1";
    public static final String EQUIPE2 = "EQUIPE2";
    public static final String ID = "ID";
    private TextView e1, e2, score1, score2;
    DatabaseHelper db;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        e1 = (TextView) findViewById(R.id.nomEquipe1);
        e2 = (TextView) findViewById(R.id.nomEquipe2);
        score1 = findViewById(R.id.pointEquipe1);
        score2 = findViewById(R.id.pointEquipe2);

        //Récupération de l'id du match
        Intent intent = getIntent();
        id = intent.getIntExtra(MainActivity.ID, id);

        //Affichage equipes et score avec Id
        db = new DatabaseHelper(this);

        Cursor data = db.getScores(id);
        if(data.getCount() == 0)
        {
            Toast.makeText(Match.this,"Error, No Data Found !!",Toast.LENGTH_LONG).show();
            return;
        }
        //StringBuffer buffer = new StringBuffer();
        while(data.moveToNext())
        {
            e1.setText(data.getString(0));
            e2.setText(data.getString(1));
            score1.setText(data.getString(2));
            score2.setText(data.getString(3));
        }

    }

    public void ChangeActivity(View view){

        switch (view.getId()){

            case R.id.buttonInfoEquipe:
                Intent i = new Intent(this, Informations.class);
                String equipe1 = e1.getText().toString();
                String equipe2 = e2.getText().toString();
                i.putExtra(EQUIPE1, equipe1);
                i.putExtra(EQUIPE2, equipe2);
                i.putExtra(ID, id);
                startActivity(i);
                break;

            case R.id.buttonLocalisation:
                i = new Intent(this, MapsActivity.class);
                //Envoyer longitude et latitude du match
                startActivity(i);
                break;

            case R.id.buttonPhoto:
                i = new Intent(this, Photos.class);
                //envoyer id du match
                startActivity(i);
                break;

            case R.id.buttonStatistiques:
                i = new Intent(this, Statistiques.class);
                //Envoyer id du match
                startActivity(i);
                break;
            default:
        }
    }

    //Menu avec bouton Home
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
