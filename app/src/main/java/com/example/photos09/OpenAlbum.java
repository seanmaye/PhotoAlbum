package com.example.photos09;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class OpenAlbum extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ImageView imageView;
    private TextView textViewTags;
    private  Button addPhotoButton;
    private Button editTagsButton;
    private Button deletePhotoButton;
    private Button goLeftButton;
    private  Button goRightButton;
    private Button movePhotoButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_album);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        imageView = (ImageView)findViewById(R.id.imageView);
        addPhotoButton=(Button)findViewById(R.id.addPhotoButton);
        textViewTags =(TextView)findViewById(R.id.textViewTags);
        editTagsButton=(Button) findViewById(R.id.editTagsButton);
        deletePhotoButton=(Button) findViewById(R.id.deletePhotoButton);
        goLeftButton=(Button) findViewById(R.id.goLeftButton);
        goRightButton=(Button) findViewById(R.id.goRightButton);
        movePhotoButton=(Button) findViewById(R.id.movePhotoButton);
      LinearLayoutManager layoutManager
                = new LinearLayoutManager(OpenAlbum.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        addPhotoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent switchActivityIntent = new Intent(OpenAlbum.this,AddPhoto.class);
                startActivity(switchActivityIntent);
            }
        });

        editTagsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent switchActivityIntent = new Intent(OpenAlbum.this,EditTags.class);
                startActivity(switchActivityIntent);
            }
        });
        movePhotoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent switchActivityIntent = new Intent(OpenAlbum.this,MovePhoto.class);
                startActivity(switchActivityIntent);
            }
        });
        deletePhotoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

            }
        });
    }
}