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

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.io.File;

public class Photos extends AppCompatActivity {
private ListView listView;
private Button createButton;
private Button openAlbum;
private Button deleteButton;
private ImageView imageView;

    public static User user = new User();
    ArrayList<String> listItems=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        File test = new File("./users.dat");
        if (test.exists()) {
            try {
                user = User.readApp();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

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

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAlbum();;
            }
        });

    }
    private void createAlbum() {
        Intent switchActivityIntent = new Intent(this, CreateAlbum.class);
        startActivity(switchActivityIntent);
    }


}