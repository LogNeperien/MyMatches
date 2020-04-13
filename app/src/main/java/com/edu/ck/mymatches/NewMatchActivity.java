package com.edu.ck.mymatches;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class NewMatchActivity extends AppCompatActivity implements LocationListener{
    DatabaseHelper db;
    EditText equipeInput, equipeInput2, entraineurInput, joueur1Input, joueur2Input, joueur3Input, joueur4Input, joueur5Input, joueur6Input;
    EditText score1, score2, entraineurInput2, dateInput;
    EditText e1_20, e1_40, e1_60, e1_80, e2_20, e2_40, e2_60, e2_80;
    Button buttonImagePhoto, buttonImageGallery;
    private String provider;
    LocationManager locationManager;

    private int GALLERY_REQUEST_CODE = 101;
    private int CAMERA_REQUEST_CODE = 100;
    private byte[] photoFinalByte;
    double lat, lng;


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
        dateInput = findViewById(R.id.editText_date);
        buttonImagePhoto = (Button) findViewById(R.id.buttonImagePhoto);
        buttonImageGallery = (Button) findViewById(R.id.buttonImageGallery);
        e1_20 = findViewById(R.id.e1_20);
        e1_40 = findViewById(R.id.e1_40);
        e1_60 = findViewById(R.id.e1_60);
        e1_80 = findViewById(R.id.e1_80);
        e2_20 = findViewById(R.id.e2_20);
        e2_40 = findViewById(R.id.e2_40);
        e2_60 = findViewById(R.id.e2_60);
        e2_80 = findViewById(R.id.e2_80);


        Log.d("coucou", "0");

        //Géolocalisation
        //Get location manager
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        //Critère pour le choix du fournisseur
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        //provider = locationManager.GPS_PROVIDER;
        Log.d("coucou", "1");


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

        Log.d("providernom", provider);


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


        if (ContextCompat.checkSelfPermission(NewMatchActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            //si on a pas la permission de prendre une photo alors on la met
            ActivityCompat.requestPermissions(NewMatchActivity.this, new String[]{Manifest.permission.CAMERA} , CAMERA_REQUEST_CODE); //on créé la permission
        }

        Log.d("coucou", "3");

        buttonImagePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), CAMERA_REQUEST_CODE);
            }});

        buttonImageGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Create an Intent with action as ACTION_PICK
                Intent intent= new Intent(Intent.ACTION_PICK);
                // Sets the type as image/*. This ensures only components of type image are selected
                intent.setType("image/*");
                //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
                String[] mimeTypes = {"image/jpeg", "image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
                // Launching the Intent
                startActivityForResult(intent,GALLERY_REQUEST_CODE);
            }});

        Log.d("coucou", "4");

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
        lat = location.getLatitude();
        lng = location.getLongitude();

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Bitmap photoFinal;
        photoFinalByte = new byte[0];
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

        if(requestCode == CAMERA_REQUEST_CODE)
        {
            photoFinal = (Bitmap) data.getExtras().get("data");
            photoFinal.compress(Bitmap.CompressFormat.JPEG,25,byteStream);
            photoFinalByte = byteStream.toByteArray();
        }
        else if(requestCode == GALLERY_REQUEST_CODE)
        {
            //data.getData returns the content URI for the selected Image
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            // Get the cursor
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            // Move to first row
            cursor.moveToFirst();
            //Get the column index of MediaStore.Images.Media.DATA
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            //Gets the String value in the column
            String imgDecodableString = cursor.getString(columnIndex);

            cursor.close();
            // Set the Image in ImageView after decoding the String
            photoFinal = (Bitmap) BitmapFactory.decodeFile(imgDecodableString);
            photoFinal.compress(Bitmap.CompressFormat.JPEG,25,byteStream);
            photoFinalByte = byteStream.toByteArray();

        }
    }


    public void addData(View view)
                    {

                        Log.d("coucou", "5");

                        boolean isInserted = db.insertDataMatch(equipeInput.getText().toString(), equipeInput2.getText().toString(),
                                score1.getText().toString(), score2.getText().toString(), entraineurInput.getText().toString(),
                                entraineurInput2.getText().toString(), joueur1Input.getText().toString(), joueur2Input.getText().toString(),
                                joueur3Input.getText().toString(), joueur4Input.getText().toString(), joueur5Input.getText().toString(),
                                joueur6Input.getText().toString(), photoFinalByte, Double.toString(lng),
                                Double.toString(lat), dateInput.getText().toString(), Integer.parseInt(e1_20.getText().toString()) ,
                                Integer.parseInt(e1_40.getText().toString()), Integer.parseInt(e1_60.getText().toString()),
                                Integer.parseInt(e1_80.getText().toString()), Integer.parseInt(e2_20.getText().toString()),
                                Integer.parseInt(e2_40.getText().toString()), Integer.parseInt(e2_60.getText().toString()),
                                Integer.parseInt(e2_80.getText().toString()));

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

