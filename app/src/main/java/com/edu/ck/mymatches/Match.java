package com.edu.ck.mymatches;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
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

    }

    public void ChangeActivity(View view){

        switch (view.getId()){

            case R.id.buttonInfoEquipe:
                Intent i = new Intent(this, InfoActivity.class);
                String equipe1 = e1.getText().toString();
                String equipe2 = e2.getText().toString();
                i.putExtra(EQUIPE1, equipe1);
                i.putExtra(EQUIPE2, equipe2);
                startActivity(i);
                break;

            /*case R.id.buttonLocalisation:
                Intent i = new Intent(this, );
                startActivity(i);
                break;
            case R.id.buttonMatch:
                Intent i = new Intent(this, );
                startActivity(i);
                break;
            case R.id.buttonPhoto:
                Intent i = new Intent(this, );
                startActivity(i);
                break;
            case R.id.buttonStatistiques:
                Intent i = new Intent(this, );
                startActivity(i);
                break;*/
            default:
        }
    }

}
