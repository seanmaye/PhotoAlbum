package com.example.photos09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeleteAlbum extends AppCompatActivity {
    private ListView listView;
    ArrayList<Album> listItems=new ArrayList<Album>();
    ArrayAdapter<Album> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_album);
        listView = (ListView)findViewById(R.id.listView);
        listItems=Photos.user.getAlbumList();
        adapter=new ArrayAdapter<Album>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Album toDelete= (Album)listView.getItemAtPosition(i);
                try {
                    Photos.user.removeAlbum(toDelete);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Intent switchActivityIntent = new Intent(DeleteAlbum.this, Photos.class);
                startActivity(switchActivityIntent);
            }
        });
    }
}