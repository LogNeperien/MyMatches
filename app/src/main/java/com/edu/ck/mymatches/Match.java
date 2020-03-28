package com.edu.ck.mymatches;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Match extends AppCompatActivity {

    public static final String EQUIPE1 = "EQUIPE1";
    public static final String EQUIPE2 = "EQUIPE2";
    private TextView e1, e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        e1 = (TextView) findViewById(R.id.nomEquipe1);
        e2 = (TextView) findViewById(R.id.nomEquipe2);

        Intent intent = getIntent();

        //Récupérer l'id du match avec getExtra pour afficher les equipes et scores
        //avec bdd

    }

    public void ChangeActivity(View view){

        switch (view.getId()){

            case R.id.buttonInfoEquipe:
                Intent i = new Intent(this, Informations.class);
                String equipe1 = e1.getText().toString();
                String equipe2 = e2.getText().toString();
                i.putExtra(EQUIPE1, equipe1);
                i.putExtra(EQUIPE2, equipe2);
                startActivity(i);
                break;

            case R.id.buttonLocalisation:
                i = new Intent(this, Localisation.class);
                //Envoyer longitude et latitude du match
                startActivity(i);
                break;

            case R.id.buttonPhoto:
                i = new Intent(this, Photos.class);
                //envoyer id du match
                startActivity(i);
                break;

            /*case R.id.buttonStatistiques:
                Intent i = new Intent(this, );
                startActivity(i);
                break;*/
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
