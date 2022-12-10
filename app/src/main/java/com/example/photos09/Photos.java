package com.example.photos09;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Photos extends AppCompatActivity {
private ListView listView;
private Button createButton;
private Button openAlbum;
private Button deleteButton;
    ArrayList<String> listItems=new ArrayList<String>();
    ArrayAdapter<String> adapter;
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
        for(int i=0; i<100; i++) {
            listItems.add("dick and balls");
        }
        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        listView.setAdapter(adapter);
    }
}