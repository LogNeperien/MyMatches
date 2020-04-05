package com.edu.ck.mymatches;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewMatchActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText equipeInput, entraineurInput, joueur1Input, joueur2Input, joueur3Input, joueur4Input, joueur5Input;
    Button ajout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_match);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();

        db = new DatabaseHelper(this);

        equipeInput = findViewById(R.id.editText_equipe);
        entraineurInput = findViewById(R.id.editText_entraineur);
        joueur1Input = findViewById(R.id.editText_joueur1);
        joueur2Input = findViewById(R.id.editText_joueur2);
        joueur3Input = findViewById(R.id.editText_joueur3);
        joueur4Input = findViewById(R.id.editText_joueur4);
        joueur5Input = findViewById(R.id.editText_joueur5);

    }

                    public void addData(View view)
                    {
                        boolean isInserted = db.insertDataEquipe(equipeInput.getText().toString(), entraineurInput.getText().toString(),
                                joueur1Input.getText().toString(), joueur2Input.getText().toString(), joueur3Input.getText().toString(),
                                joueur4Input.getText().toString(), joueur5Input.getText().toString());
                        if(isInserted == true)
                        {
                            Toast.makeText(NewMatchActivity.this, "Data Inserted !!",Toast.LENGTH_LONG).show();
                        }
                        else{ Toast.makeText(NewMatchActivity.this, "Data Not Inserted !!",Toast.LENGTH_LONG).show(); }
                    }

                    public void affichageDB(View view)
                    {
                        Cursor data = db.getAllData();
                        if(data.getCount() == 0)
                        {
                            Toast.makeText(NewMatchActivity.this,"Error, No Data Found !!",Toast.LENGTH_LONG).show();
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while(data.moveToNext())
                        {
                            buffer.append("Id : " + data.getString(0) + "\n ");
                            buffer.append("Equipe : " + data.getString(1) + "\n ");
                            buffer.append("Entraineur : " + data.getString(2) + "\n ");
                            buffer.append("J1 : " + data.getString(3) + "\n ");
                            buffer.append("J2 : " + data.getString(4) + "\n ");
                            buffer.append("J3 : " + data.getString(5) + "\n ");
                            buffer.append("J4 : " + data.getString(6) + "\n ");
                            buffer.append("J5 : " + data.getString(7) + "\n ");
                        }
                        Toast.makeText(NewMatchActivity.this, buffer.toString(), Toast.LENGTH_LONG).show();
                    }
    }

