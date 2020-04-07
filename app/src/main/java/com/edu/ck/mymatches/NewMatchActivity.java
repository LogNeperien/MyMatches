package com.edu.ck.mymatches;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewMatchActivity extends AppCompatActivity implements LocationListener{
    DatabaseHelper db;
    EditText equipeInput, equipeInput2, entraineurInput, joueur1Input, joueur2Input, joueur3Input, joueur4Input, joueur5Input, joueur6Input;
    EditText score1, score2, entraineurInput2, photoInput, dateInput;
    private String provider;
    LocationManager locationManager;
    int lat, lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_match);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();

        db = new DatabaseHelper(this);

        //Initialisation des Views
        equipeInput = findViewById(R.id.editText_equipe);
        equipeInput2 = findViewById(R.id.editText_equipe2);
        entraineurInput = findViewById(R.id.editText_entraineur);
        entraineurInput2 = findViewById(R.id.editText_entraineur2);
        score1 = findViewById(R.id.editText_score1);
        score2 = findViewById(R.id.editText_score2);
        joueur1Input = findViewById(R.id.editText_joueur1);
        joueur2Input = findViewById(R.id.editText_joueur2);
        joueur3Input = findViewById(R.id.editText_joueur3);
        joueur4Input = findViewById(R.id.editText_joueur1_e2);
        joueur5Input = findViewById(R.id.editText_joueur2_e2);
        joueur6Input = findViewById(R.id.editText_joueur3_e2);
        photoInput = findViewById(R.id.editText_photo);
        dateInput = findViewById(R.id.editText_date);

        //Géolocalisation
        //Get location manager
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        //Critère pour le choix du fournisseur
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        //provider = locationManager.GPS_PROVIDER;
        Log.d("coucou", "0");

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET
                }, 10);

            }
            return;
        }

        Location location = locationManager.getLastKnownLocation(provider);
        Log.d("providernom", provider);

        if (location != null) {
            //System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);
        } else {
            //Message d'erreur
            System.out.println("Provider not found.");
            Log.d("providernotfound", "mince");
        }

    }

    ///GEOLOCALISATION
    /* Request updates at startup */
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
           /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET
                }, 11);
            }*/

            return;
        }
        locationManager.requestLocationUpdates(provider, 4000, 1, (LocationListener) this);
    }

    /* Remove the locationlistener updates when Activity is paused */
    @Override
    protected void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET
                }, 12);
            }*/

            return;
        }
        locationManager.removeUpdates(this);
    }

    public void onLocationChanged(Location location)
    {
        lat = (int) location.getLatitude();
        lng = (int) location.getLongitude();

        Log.d("GPSok", "lat" + lat + "et long " + lng);
        //Stockage BDD

    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }

    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();
    }

    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
    }


    public void addData(View view)
                    {
                        boolean isInserted = db.insertDataMatch(equipeInput.getText().toString(), equipeInput2.getText().toString(),
                                score1.getText().toString(), score2.getText().toString(), entraineurInput.getText().toString(),
                                entraineurInput2.getText().toString(), joueur1Input.getText().toString(), joueur2Input.getText().toString(),
                                joueur3Input.getText().toString(), joueur4Input.getText().toString(), joueur5Input.getText().toString(),
                                joueur6Input.getText().toString(), photoInput.getText().toString(), Integer.toString(lng),
                                Integer.toString(lat), dateInput.getText().toString());
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
                            buffer.append("Equipe2 : " + data.getString(2) + "\n ");
                            buffer.append("score1 : " + data.getString(3) + "\n ");
                            buffer.append("score2 : " + data.getString(4) + "\n ");
                            buffer.append("long : " + data.getString(14) + "\n ");
                            buffer.append("lat : " + data.getString(15) + "\n ");

                        }
                        Toast.makeText(NewMatchActivity.this, buffer.toString(), Toast.LENGTH_LONG).show();
                    }
    }

