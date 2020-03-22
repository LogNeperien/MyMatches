package com.edu.ck.mymatches;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    private TextView e1, e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        /*String operation = intent.getStringExtra(MainActivity.EXTRA_OPERATION);
        String resultat = intent.getStringExtra(MainActivity.EXTRA_RESULTAT);
        String message1 = "Opération précédente : " + operation;
        String message2 = "Résultat précédent : " + resultat;
        opeText.setText(message1);
        resText.setText(message2);*/

    }

}
