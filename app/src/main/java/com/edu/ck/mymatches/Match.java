package com.edu.ck.mymatches;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Match extends AppCompatActivity {

    public static final String EQUIPE1 = "EQUIPE1";
    public static final String EQUIPE2 = "EQUIPE2";
    public static final String ID = "ID";
    public static final String PHOTO = "PHOTO";
    private TextView e1, e2, score1, score2;
    private ImageView image;
    DatabaseHelper db;
    int id;
    private byte[] photoByte;
    private int e1_20 = -1;
    private int e1_40 = -1;
    private int e1_60 = -1;
    private int  e1_80 = -1;
    private int e2_20 = -1;
    private int e2_40 = -1;
    private int e2_60 = -1;
    private int e2_80 = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        e1 = (TextView) findViewById(R.id.nomEquipe1);
        e2 = (TextView) findViewById(R.id.nomEquipe2);
        score1 = findViewById(R.id.pointEquipe1);
        score2 = findViewById(R.id.pointEquipe2);
        image = findViewById(R.id.imageView);

        //Récupération de l'id du match
        Intent intent = getIntent();
        id = intent.getIntExtra(MainActivity.ID, id);

        //Affichage equipes et score avec Id
        db = new DatabaseHelper(this);

        Cursor data = db.getScores(id);
        Cursor scores = db.getScoresStat(id);
        if(data.getCount() == 0 || scores.getCount() == 0)
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
            photoByte = data.getBlob(4);
        }

        while(scores.moveToNext())
        {
            e1_20 = scores.getInt(0);
            e1_40 = scores.getInt(1);
            e1_60 = scores.getInt(2);
            e1_80 = scores.getInt(3);
            e2_20 = scores.getInt(4);
            e2_40 = scores.getInt(5);
            e2_60 = scores.getInt(6);
            e2_80 = scores.getInt(7);
        }
    }

    public void ChangeActivity(View view){

        String equipe1;
        String equipe2;

        switch (view.getId()){

            case R.id.buttonInfoEquipe:
                Intent i = new Intent(this, Informations.class);
                equipe1 = e1.getText().toString();
                equipe2 = e2.getText().toString();
                i.putExtra(EQUIPE1, equipe1);
                i.putExtra(EQUIPE2, equipe2);
                i.putExtra(ID, id);
                startActivity(i);
                break;

            case R.id.buttonLocalisation:
                i = new Intent(this, MapsActivity.class);
                i.putExtra(ID, id);
                startActivity(i);
                break;

            case R.id.buttonPhoto:
                i = new Intent(this, Photos.class);
                i.putExtra(ID, id);
                i.putExtra(PHOTO, photoByte);
                startActivity(i);
                break;

            case R.id.buttonStatistiques:
                i = new Intent(this, Statistiques.class);
                equipe1 = e1.getText().toString();
                equipe2 = e2.getText().toString();

                i.putExtra(EQUIPE1, equipe1);
                i.putExtra(EQUIPE2, equipe2);

                i.putExtra("e1_20", e1_20);
                i.putExtra("e1_40", e1_40);
                i.putExtra("e1_60", e1_60);
                i.putExtra("e1_80", e1_80);
                i.putExtra("e2_20", e2_20);
                i.putExtra("e2_40", e2_40);
                i.putExtra("e2_60", e2_60);
                i.putExtra("e2_80", e2_80);

                i.putExtra(ID, id);

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
