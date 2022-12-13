package com.example.photos09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class Photos extends AppCompatActivity {
private ListView listView;
private Button createButton;
private Button openAlbum;
private Button deleteButton;
private ImageView imageView;
    ArrayList<String> listItems=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    int SELECT_PICTURE = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photos_main);
        listView =(ListView) findViewById(R.id.listView);
        createButton = (Button)findViewById(R.id.createButton);
        openAlbum = (Button)findViewById(R.id.openAlbum);
        deleteButton = (Button)findViewById(R.id.deleteButton);

        openAlbum.setText("Open Album");
        createButton.setText("Create Album");
        deleteButton.setText("Delete Album");
        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        listView.setAdapter(adapter);


        // handle the Choose Image button to trigger
        // the image chooser function
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });
    }

    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

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
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    listItems.add(selectedImageUri.toString());
                    adapter=new ArrayAdapter<String>(this,
                            android.R.layout.simple_list_item_1,
                            listItems);
                    listView.setAdapter(adapter);
                    imageView.setImageURI(selectedImageUri);
                }
            }
        }
    }

}