package com.example.photos09;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class Popup extends AppCompatActivity {
    private ImageView previewer;
    private Button deleteButton;
    private Button moveButton;
    private Button editTags;
    private Button displayButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_window);
        previewer=(ImageView) findViewById(R.id.imageView3);
        deleteButton=(Button) findViewById(R.id.popDelete);
        moveButton=(Button) findViewById(R.id.popMove);
        editTags=(Button) findViewById(R.id.popEdit);
        displayButton=(Button)findViewById(R.id.popDisplay);
        previewer.setImageURI(OpenAlbum.passPhoto.getUri());
        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try {
                    Photos.passAlbum.removePhoto(OpenAlbum.passPhoto);
                    Intent switchActivityIntent = new Intent(Popup.this,OpenAlbum.class);
                    startActivity(switchActivityIntent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            });

        moveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent switchActivityIntent = new Intent(Popup.this,MovePhoto.class);
                startActivity(switchActivityIntent);
            }
        });

        editTags.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent switchActivityIntent = new Intent(Popup.this,EditTags.class);
                startActivity(switchActivityIntent);
            }
        });
        displayButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                OpenAlbum.displayPhoto=OpenAlbum.passIndex;
                Intent switchActivityIntent = new Intent(Popup.this,OpenAlbum.class);
                startActivity(switchActivityIntent);
            }
        });
    }
}
