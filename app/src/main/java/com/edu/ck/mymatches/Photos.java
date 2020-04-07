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

    //variable de la classe
    Button buttonImage;
    ImageView imageDisplay;

    private int GALLERY_REQUEST_CODE = 101;
    private int CAMERA_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        buttonImage = (Button) findViewById(R.id.buttonImage);
        imageDisplay = (ImageView) findViewById(R.id.photo1);

        if (ContextCompat.checkSelfPermission(Photos.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            //si on a pas la permission de prendre une photo
            ActivityCompat.requestPermissions(Photos.this, new String[]{Manifest.permission.CAMERA} , CAMERA_REQUEST_CODE); //on créé la permission
        }

        buttonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), CAMERA_REQUEST_CODE);
                Intent intent=new Intent(Intent.ACTION_PICK);
                // Sets the type as image/*. This ensures only components of type image are selected
                intent.setType("image/*");
                //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
                String[] mimeTypes = {"image/jpeg", "image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
                // Launching the Intent
                startActivityForResult(intent,GALLERY_REQUEST_CODE);
            }
        }
        );
    }

    //source : https://androidclarified.com/pick-image-gallery-camera-android/
    //explication : l'activité dit que l'intent ne devra retourner que des image.png ou jpeg
    //              puis une fois que l'image est selectionnée, start activity for result envoie un callback
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Bitmap photoFinal;
        if(requestCode == CAMERA_REQUEST_CODE)
        {
            //début toast
            Context context = getApplicationContext();
            CharSequence text = "camera";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            //fin toast

            photoFinal = (Bitmap) data.getExtras().get("data");
            imageDisplay.setImageBitmap(photoFinal);
        }
        else if(requestCode == GALLERY_REQUEST_CODE)
        {
            //debut toast
            Context context = getApplicationContext();
            CharSequence text = "gallery";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            //fin toast

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
