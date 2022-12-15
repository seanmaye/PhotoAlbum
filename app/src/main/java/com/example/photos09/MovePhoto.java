package com.example.photos09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovePhoto extends AppCompatActivity {
    private ListView listView;
    private ImageView previewImage;
    ArrayList<Album> listItems=new ArrayList<Album>();
    ArrayAdapter<Album> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_photo);
        previewImage=(ImageView) findViewById(R.id.previewImage);
        listView=(ListView) findViewById(R.id.listView);
        listItems=Photos.user.getAlbumList();
        adapter=new ArrayAdapter<Album>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        listView.setAdapter(adapter);
        previewImage.setImageURI(OpenAlbum.passPhoto.getUri());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Album toSend= (Album)listView.getItemAtPosition(i);
                if(toSend.getName().equals(Photos.passAlbum.getName())){
                    Toast.makeText(MovePhoto.this, "Cannot Move photo to its own album!", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    Photos.passAlbum.movePhoto(OpenAlbum.passPhoto,toSend);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Intent switchActivityIntent = new Intent(MovePhoto.this, OpenAlbum.class);
                startActivity(switchActivityIntent);
            }
        });

    }
}