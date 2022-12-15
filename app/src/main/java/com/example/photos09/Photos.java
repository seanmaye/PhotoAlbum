package com.example.photos09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

public class Photos extends AppCompatActivity {
private ListView listView;
private Button createButton;
private Button deleteButton;
static Album passAlbum;
private Button searchButton;
    public static User user = new User();
    ArrayList<Album> listItems=new ArrayList<Album>();
    ArrayAdapter<Album> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
        File test = new File(path, "." + "users.dat");
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
        deleteButton = (Button)findViewById(R.id.deleteButton);
        searchButton = (Button)findViewById(R.id.searchButton);
        createButton.setText("Create Album");
        deleteButton.setText("Delete Album");
        listItems=user.getAlbumList();
        adapter=new ArrayAdapter<Album>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        listView.setAdapter(adapter);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAlbum();;
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAlbum();
            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchActivityIntent = new Intent(Photos.this, Search.class);
                startActivity(switchActivityIntent);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                 passAlbum= (Album)listView.getItemAtPosition(i);


                Intent switchActivityIntent = new Intent(Photos.this, OpenAlbum.class);
                startActivity(switchActivityIntent);
            }
        });


    }
    private void createAlbum() {
        Intent switchActivityIntent = new Intent(this, CreateAlbum.class);
        startActivity(switchActivityIntent);
    }
    private void deleteAlbum() {
        if(user.getAlbumList().size()>0) {
            Intent switchActivityIntent = new Intent(this, DeleteAlbum.class);
            startActivity(switchActivityIntent);
        }else{
            Toast.makeText(Photos.this, "No Albums to delete", Toast.LENGTH_SHORT).show();
            return;
        }
    }

}