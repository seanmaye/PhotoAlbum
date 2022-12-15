package com.example.photos09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class AddPhoto extends AppCompatActivity {
    private Button selectButton;
    private Button addButton;
    private ImageView previewAdd;
    int SELECT_PICTURE = 200;
    private Photo toAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);
        selectButton = (Button) findViewById(R.id.selectButton);
        addButton = (Button) findViewById(R.id.addButton);
        previewAdd = (ImageView)findViewById(R.id.previewAdd);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Photos.passAlbum.addPhoto(toAdd);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent switchActivityIntent = new Intent(AddPhoto.this, OpenAlbum.class);
                startActivity(switchActivityIntent);
            }
        });
    }
    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_OPEN_DOCUMENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                getContentResolver().takePersistableUriPermission(selectedImageUri, Intent.FLAG_GRANT_READ_URI_PERMISSION
                        | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                if (null != selectedImageUri) {
                    previewAdd.setImageURI(selectedImageUri);
                    toAdd = new Photo(selectedImageUri.toString(),"Location","Default");

                }
            }
        }
    }
}