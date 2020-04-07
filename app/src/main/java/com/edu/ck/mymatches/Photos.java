package com.edu.ck.mymatches;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Photos extends AppCompatActivity {

    ///VARIABLE DE LA CLASSE
    Button buttonImagePhoto;
    Button buttonImageGallery;
    ImageView imageDisplay;

    private int GALLERY_REQUEST_CODE = 101;
    private int CAMERA_REQUEST_CODE = 100;

    ///PRIVATE FONCTION
    private void pickFromGallery(){
        //Create an Intent with action as ACTION_PICK
        Intent intent=new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
        // Launching the Intent
        startActivityForResult(intent,GALLERY_REQUEST_CODE);
    }

    ///PUBLIC FONCTION
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        buttonImagePhoto = (Button) findViewById(R.id.buttonImage);
        buttonImageGallery = (Button) findViewById(R.id.boutonImageGallery);
        imageDisplay = (ImageView) findViewById(R.id.photo1);

        if (ContextCompat.checkSelfPermission(Photos.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            //si on a pas la permission de prendre une photo alors on la met
            ActivityCompat.requestPermissions(Photos.this, new String[]{Manifest.permission.CAMERA} , CAMERA_REQUEST_CODE); //on créé la permission
        }


        buttonImagePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), CAMERA_REQUEST_CODE);
        }});

        buttonImageGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickFromGallery();
            }});
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Bitmap photoFinal;
        if(requestCode == CAMERA_REQUEST_CODE)
        {
            photoFinal = (Bitmap) data.getExtras().get("data");
            imageDisplay.setImageBitmap(photoFinal);
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
            imageDisplay.setImageBitmap(photoFinal);
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
